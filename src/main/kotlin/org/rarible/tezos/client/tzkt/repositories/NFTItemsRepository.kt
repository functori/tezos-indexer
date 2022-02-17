package org.rarible.tezos.client.tzkt.repositories

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTItems
import org.rarible.tezos.indexer.model.Part
import org.rarible.tezos.indexer.model.items.NftItem
import org.rarible.tezos.indexer.model.items.NftItemMeta.Companion.parseNFTMetadata
import java.time.Instant

class NFTItemsRepository {
    companion object {

        class NFTItemsRepositoryException(msg: String, val code: Int) : Exception(msg)
        private const val defaultSize: Int = 50

        private fun processItem(item: ResultRow, includeMeta: Boolean?): NftItem? {
            return NftItem(
                id = item[NFTItems.id],
                contract = item[NFTItems.contract],
                tokenId = item[NFTItems.tokenId],
                creators = listOf(
                    Part(item[NFTItems.creators], 10000)
                ),
                supply = item[NFTItems.supply],
                lazySupply = "0",
                owners = item[NFTItems.owners].split(",").toList(),
                royalties = emptyList(),
                date = item[NFTItems.date],
                mintedAt = item[NFTItems.mintedAt],
                deleted = (item[NFTItems.supply] == item[NFTItems.burned]),
                onchainRoyalties = false,
                meta = if(includeMeta == true){parseNFTMetadata(item[NFTItems.meta])} else null
            )
        }

        private fun applyFilters(query: Query, lastUpdateFrom: String?, lastUpdateTo: String?, continuation: String?, size: Int?): Query {
            if(continuation != null){
                val continuationArgs = continuation.split('_')
                if(continuationArgs.size != 2){
                    throw NFTItemsRepositoryException(
                        "Continuation pattern must be TIMESTAMP_CONTRACT:ID",
                        500
                    )
                }
                val timestamp = continuationArgs[0].toLong()
                val id = continuationArgs[1]
                query.andWhere {
                    (NFTItems.date less Instant.ofEpochMilli(timestamp)) and (NFTItems.id less id)
                }
            }

            if(lastUpdateFrom != null){
                query.andWhere {
                    (NFTItems.date greater Instant.parse(lastUpdateFrom))
                }
            }
            if(lastUpdateTo != null) {
                query.andWhere {
                    (NFTItems.date less Instant.parse(lastUpdateTo))
                }
            }

            if(size != null){
                if(size <= 0 || size > 1000){
                    throw NFTActivityRepository.Companion.NFTActivityRepositoryException(
                        "Size can't be <= 0 or > 1000",
                        500
                    )
                }
                query.limit(size)
            } else {
                query.limit(defaultSize)
            }

            query.orderBy(Pair(NFTItems.date, SortOrder.DESC), Pair(NFTItems.id, SortOrder.DESC))

            return query
        }

        fun queryAllNFTItems(lastUpdateFrom: String?, lastUpdateTo: String?, showDeleted: Boolean?, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
            var result: MutableList<NftItem> = mutableListOf()
            var query =  NFTItems.selectAll()

            query = applyFilters(query, lastUpdateFrom, lastUpdateTo, continuation, size)

            if(showDeleted == false){
                query.andWhere { NFTItems.supply neq NFTItems.burned }
            }

            transaction {
                query.forEach {
                    var item: NftItem? =
                        processItem(it, includeMeta) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                    result.add(item!!)
                }
            }
            return result
        }

        fun queryNFTItemsByCollection(collection: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
            var result: MutableList<NftItem> = mutableListOf()
            var query =  NFTItems.select {
                NFTItems.contract eq collection
            }

            query = applyFilters(query, null, null, continuation, size)

            transaction {
                query.forEach {
                    var item: NftItem? =
                        processItem(it, includeMeta) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                    result.add(item!!)
                }
            }
            return result
        }

        fun queryNFTItemsByCreator(creator: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
            var result: MutableList<NftItem> = mutableListOf()
            var query =  NFTItems.select {
                NFTItems.creators like "%$creator%"
            }

            query = applyFilters(query, null, null, continuation, size)

            transaction {
                query.forEach {
                    var item: NftItem? =
                        processItem(it, includeMeta) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                    result.add(item!!)
                }
            }
            return result
        }

        fun queryNFTItemsByOwner(owner: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
            var result: MutableList<NftItem> = mutableListOf()
            var query =  NFTItems.select {
                NFTItems.owners like "%$owner%"
            }

            query = applyFilters(query, null, null, continuation, size)

            transaction {
                query.forEach {
                    var item: NftItem? =
                        processItem(it, includeMeta) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                    result.add(item!!)
                }
            }
            return result
        }

//        fun queryNFTActivitiesByUser(users: List<String>, types: List<NftActivityFilterUserType>, continuation: String?, size: Int?, sort: ActivitySort?): List<NftActivityElt> {
//            var result: MutableList<NftActivityElt> = mutableListOf()
//            var requestTypes: MutableSet<String> = mutableSetOf()
//            types.forEach{
//                when(it){
//                    NftActivityFilterUserType.TRANSFERTO,NftActivityFilterUserType.TRANSFERFROM ->{
//                        requestTypes.add(NftActivity.NFTActivityType.transfer.value)
//                    }
//                    NftActivityFilterUserType.MINT -> requestTypes.add(NftActivity.NFTActivityType.mint.value)
//                    NftActivityFilterUserType.BURN -> requestTypes.add(NftActivity.NFTActivityType.burn.value)
//                }
//            }
//            var query =  NFTActivities.select{
//                (type inList requestTypes)
//            }
//            if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && !types.contains(NftActivityFilterUserType.TRANSFERTO)){
//                query.andWhere { (NFTActivities.from inList users)}
//            }
//            else if(!types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
//                query.andWhere { (NFTActivities.to inList users) }
//            }
//            else if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
//                query.andWhere { (NFTActivities.from inList users) or (NFTActivities.to inList users) }
//            }
//
//            query = applyFilters(query, continuation, size, sort)
//
//            transaction {
//                query.forEach {
//                    var activity: NftActivityElt? =
//                        processActivity(it) ?: throw NFTActivityRepository.Companion.NFTActivityRepositoryException(
//                            "Could not parse activity: $it",
//                            500
//                        )
//                    result.add(activity!!)
//                }
//            }
//
//            return result
//        }
//
//        fun queryNFTActivitiesByItem(contract: String, tokenId: String?, types: List<NftActivityFilterAllType>, continuation: String?, size: Int?, sort: ActivitySort?): List<NftActivityElt> {
//            var result: MutableList<NftActivityElt> = mutableListOf()
//
//            var query =  NFTActivities.select{
//                (type inList types.map { it.value }) and (NFTActivities.contract eq contract)
//            }
//            if(!tokenId.isNullOrEmpty()){
//                query.andWhere { (NFTActivities.tokenId eq tokenId)}
//            }
//
//            query = applyFilters(query, continuation, size, sort)
//
//            transaction {
//                query.limit(100).forEach {
//                    var activity: NftActivityElt? =
//                        processActivity(it) ?: throw NFTActivityRepository.Companion.NFTActivityRepositoryException(
//                            "Could not parse activity: $it",
//                            500
//                        )
//                    result.add(activity!!)
//                }
//            }
//
//            return result
//        }
    }
}