package org.rarible.tezos.client.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTActivities
import org.rarible.tezos.client.tzkt.models.NFTActivities.type
import org.rarible.tezos.indexer.model.ActivitySort
import org.rarible.tezos.indexer.model.activities.NftActivity
import org.rarible.tezos.indexer.model.activities.NftActivityElt
import org.rarible.tezos.indexer.model.activities.NftBaseActivityElt
import org.rarible.tezos.indexer.model.activities.NftMintBurnActivityElt
import org.rarible.tezos.indexer.model.activities.NftTransferActivityElt
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterAll
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterAllType
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterByItem
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterUserType
import java.math.BigDecimal
import java.time.Instant

class NFTActivityRepository {
    companion object {

        class NFTActivityRepositoryException(msg: String, val code: Int) : Exception(msg)
        private val defaultSize: Int = 50

        private fun processActivity(activity: ResultRow): NftActivityElt? {
            var result: NftActivityElt? = null
            when (activity[type]) {
                NftActivity.NFTActivityType.mint.value -> {
                    result =
                        NftActivityElt(
                            activity[NFTActivities.txHash],
                            activity[NFTActivities.date],
                            "RARIBLE",
                            NftMintBurnActivityElt(
                                NftActivity.NFTActivityType.mint,
                                activity[NFTActivities.to],
                                activity[NFTActivities.contract],
                                activity[NFTActivities.tokenId],
                                BigDecimal(activity[NFTActivities.value]),
                                activity[NFTActivities.txHash],
                                activity[NFTActivities.blockHash],
                                activity[NFTActivities.blockNumber]
                            )
                        )

                }
                NftActivity.NFTActivityType.burn.value -> {
                    result =
                        NftActivityElt(
                            activity[NFTActivities.txHash],
                            activity[NFTActivities.date],
                            "RARIBLE",
                            NftMintBurnActivityElt(
                                NftActivity.NFTActivityType.burn,
                                activity[NFTActivities.to],
                                activity[NFTActivities.contract],
                                activity[NFTActivities.tokenId],
                                BigDecimal(activity[NFTActivities.value]),
                                activity[NFTActivities.txHash],
                                activity[NFTActivities.blockHash],
                                activity[NFTActivities.blockNumber]
                            )
                        )

                }
                NftActivity.NFTActivityType.transfer.value -> {
                    result =
                        NftActivityElt(
                            activity[NFTActivities.txHash],
                            activity[NFTActivities.date],
                            "RARIBLE",
                            NftTransferActivityElt(
                                NftActivity.NFTActivityType.transfer,
                                NftBaseActivityElt(
                                    activity[NFTActivities.to],
                                    activity[NFTActivities.contract],
                                    activity[NFTActivities.tokenId],
                                    BigDecimal(activity[NFTActivities.value]),
                                    activity[NFTActivities.txHash],
                                    activity[NFTActivities.blockHash],
                                    activity[NFTActivities.blockNumber]
                                ),
                                activity[NFTActivities.from],
                            )
                        )

                }
            }
            return result
        }

        private fun applyFilters(query: Query, continuation: String?, size: Int?, sort: ActivitySort?): Query {
            if(continuation != null){
                val continuationArgs = continuation.split('_')
                if(continuationArgs.size != 2){
                    throw NFTActivityRepositoryException("Continuation pattern must be TIMESTAMP_HASH", 500)
                }
                val timestamp = continuationArgs[0].toLong()
                val hash = continuationArgs[1]
                if(sort != null && sort == ActivitySort.LATESTFIRST){
                    query.andWhere {
                        (NFTActivities.txHash less  hash) and (NFTActivities.date less Instant.ofEpochMilli(timestamp))
                    }
                } else {
                    query.andWhere {
                        (NFTActivities.txHash greater hash) and (NFTActivities.date greater Instant.ofEpochMilli(timestamp))
                    }
                }

            }

            if(size != null){
                if(size <= 0 || size > 1000){
                    throw NFTActivityRepositoryException("Size can't be <= 0 or > 1000", 500)
                }
                query.limit(size)
            } else {
                query.limit(defaultSize)
            }

            if(sort != null){
                if(sort == ActivitySort.LATESTFIRST){
                    query.orderBy(Pair(NFTActivities.date, SortOrder.DESC), Pair(NFTActivities.txHash, SortOrder.DESC))
                } else {
                    query.orderBy(Pair(NFTActivities.date, SortOrder.ASC), Pair(NFTActivities.txHash, SortOrder.ASC))
                }
            } else {
                query.orderBy(Pair(NFTActivities.date, SortOrder.ASC), Pair(NFTActivities.txHash, SortOrder.ASC))
            }

            return query
        }

        fun queryAllNFTActivities(types: List<NftActivityFilterAllType>, continuation: String?, size: Int?, sort: ActivitySort?): List<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            var query =  NFTActivities.select{
                (type inList types.map{ it.value })
            }

            query = applyFilters(query, continuation, size, sort)

            transaction {
                query.forEach {
                    var activity: NftActivityElt? =
                        processActivity(it) ?: throw NFTActivityRepositoryException("Could not parse activity: $it", 500)
                    result.add(activity!!)
                }
            }
            return result
        }

        fun queryNFTActivitiesByUser(users: List<String>, types: List<NftActivityFilterUserType>, continuation: String?, size: Int?, sort: ActivitySort?): List<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            var requestTypes: MutableSet<String> = mutableSetOf()
            types.forEach{
                when(it){
                    NftActivityFilterUserType.TRANSFERTO,NftActivityFilterUserType.TRANSFERFROM ->{
                        requestTypes.add(NftActivity.NFTActivityType.transfer.value)
                    }
                    NftActivityFilterUserType.MINT -> requestTypes.add(NftActivity.NFTActivityType.mint.value)
                    NftActivityFilterUserType.BURN -> requestTypes.add(NftActivity.NFTActivityType.burn.value)
                }
            }
            var query =  NFTActivities.select{
                (type inList requestTypes)
            }
            if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && !types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivities.from inList users)}
            }
            else if(!types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivities.to inList users) }
            }
            else if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivities.from inList users) or (NFTActivities.to inList users) }
            }

            query = applyFilters(query, continuation, size, sort)

            transaction {
                query.forEach {
                    var activity: NftActivityElt? =
                        processActivity(it) ?: throw NFTActivityRepositoryException("Could not parse activity: $it", 500)
                    result.add(activity!!)
                }
            }

            return result
        }

        fun queryNFTActivitiesByItem(contract: String, tokenId: String?, types: List<NftActivityFilterAllType>, continuation: String?, size: Int?, sort: ActivitySort?): List<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()

            var query =  NFTActivities.select{
                (type inList types.map { it.value }) and (NFTActivities.contract eq contract)
            }
            if(!tokenId.isNullOrEmpty()){
                query.andWhere { (NFTActivities.tokenId eq tokenId)}
            }

            query = applyFilters(query, continuation, size, sort)

            transaction {
                query.limit(100).forEach {
                    var activity: NftActivityElt? =
                        processActivity(it) ?: throw NFTActivityRepositoryException("Could not parse activity: $it", 500)
                    result.add(activity!!)
                }
            }

            return result
        }
    }
}