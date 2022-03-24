package com.rarible.protocol.tezos.api.util.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTItemDTO
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTItemDTO.id
import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.model.items.NftItemMeta
import com.rarible.protocol.tezos.api.model.items.NftItemMeta.Companion.parseNFTMetadata
import java.time.Instant
import java.time.OffsetDateTime

class NFTItemsRepository {
    companion object {

        class NFTItemsRepositoryException(msg: String, val code: Int) : Exception(msg)
        private const val defaultSize: Int = 50

        private fun processItem(item: ResultRow, includeMeta: Boolean?): NftItem? {
            return NftItem(
                id = item[id],
                contract = item[NFTItemDTO.contract],
                tokenId = item[NFTItemDTO.tokenId],
                creators = listOf(
                    Part(item[NFTItemDTO.creators], 10000)
                ),
                supply = item[NFTItemDTO.supply],
                lazySupply = "0",
                owners = item[NFTItemDTO.owners].split(",").toList(),
                royalties = emptyList(),
                date = OffsetDateTime.parse(item[NFTItemDTO.date].toString()),
                mintedAt = OffsetDateTime.parse(item[NFTItemDTO.mintedAt].toString()),
                deleted = (item[NFTItemDTO.supply] == item[NFTItemDTO.burned]),
                onchainRoyalties = false,
                meta = if(includeMeta == true && !item[NFTItemDTO.meta].isNullOrEmpty()){parseNFTMetadata(item[NFTItemDTO.meta])} else null
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
                    (NFTItemDTO.date less Instant.ofEpochMilli(timestamp)) and (NFTItemDTO.id less id)
                }
            }

            if(lastUpdateFrom != null){
                query.andWhere {
                    (NFTItemDTO.date greater Instant.parse(lastUpdateFrom))
                }
            }
            if(lastUpdateTo != null) {
                query.andWhere {
                    (NFTItemDTO.date less Instant.parse(lastUpdateTo))
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

            query.orderBy(Pair(NFTItemDTO.date, SortOrder.DESC), Pair(id, SortOrder.DESC))

            return query
        }

        fun queryAllNFTItems(lastUpdateFrom: String?, lastUpdateTo: String?, showDeleted: Boolean?, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
            var result: MutableList<NftItem> = mutableListOf()
            var query =  NFTItemDTO.selectAll()

            query = applyFilters(query, lastUpdateFrom, lastUpdateTo, continuation, size)

            if(showDeleted == false){
                query.andWhere { NFTItemDTO.supply neq NFTItemDTO.burned }
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
            var query =  NFTItemDTO.select {
                NFTItemDTO.contract eq collection
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
            var query =  NFTItemDTO.select {
                NFTItemDTO.creators like "%$creator%"
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
            var query =  NFTItemDTO.select {
                NFTItemDTO.owners like "%$owner%"
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

        fun queryNFTItem(id: String, includeMeta: Boolean?): NftItem? {
            var result: NftItem? = null
            var query =  NFTItemDTO.select {
                NFTItemDTO.id eq id
            }

            transaction {
                query.forEach {
                    result =
                        processItem(it, includeMeta) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                }
            }
            return result
        }

        fun queryNFTItemMeta(id: String): NftItemMeta? {
            var result: NftItemMeta? = null
            var query =  NFTItemDTO.slice(NFTItemDTO.meta).select {
                NFTItemDTO.id eq id
            }

            transaction {
                query.forEach {
                    var item: NftItemMeta? =
                        parseNFTMetadata(it[NFTItemDTO.meta]) ?: throw NFTItemsRepositoryException(
                            "Could not parse activity: $it",
                            500
                        )
                    result = item
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
