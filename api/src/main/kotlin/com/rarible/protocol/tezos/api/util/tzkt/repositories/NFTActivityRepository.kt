package com.rarible.protocol.tezos.api.util.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTActivityDTO
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTActivityDTO.type
import com.rarible.protocol.tezos.api.model.ActivitySort
import com.rarible.protocol.tezos.api.model.activities.NftActivity
import com.rarible.protocol.tezos.api.model.activities.NftActivityElt
import com.rarible.protocol.tezos.api.model.activities.NftBaseActivityElt
import com.rarible.protocol.tezos.api.model.activities.NftMintBurnActivityElt
import com.rarible.protocol.tezos.api.model.activities.NftTransferActivityElt
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterAllType
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterUserType
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
                            activity[NFTActivityDTO.txHash],
                            activity[NFTActivityDTO.date],
                            "RARIBLE",
                            NftMintBurnActivityElt(
                                NftActivity.NFTActivityType.mint,
                                activity[NFTActivityDTO.to],
                                activity[NFTActivityDTO.contract],
                                activity[NFTActivityDTO.tokenId],
                                BigDecimal(activity[NFTActivityDTO.value]),
                                activity[NFTActivityDTO.txHash],
                                activity[NFTActivityDTO.blockHash],
                                activity[NFTActivityDTO.blockNumber]
                            )
                        )

                }
                NftActivity.NFTActivityType.burn.value -> {
                    result =
                        NftActivityElt(
                            activity[NFTActivityDTO.txHash],
                            activity[NFTActivityDTO.date],
                            "RARIBLE",
                            NftMintBurnActivityElt(
                                NftActivity.NFTActivityType.burn,
                                activity[NFTActivityDTO.to],
                                activity[NFTActivityDTO.contract],
                                activity[NFTActivityDTO.tokenId],
                                BigDecimal(activity[NFTActivityDTO.value]),
                                activity[NFTActivityDTO.txHash],
                                activity[NFTActivityDTO.blockHash],
                                activity[NFTActivityDTO.blockNumber]
                            )
                        )

                }
                NftActivity.NFTActivityType.transfer.value -> {
                    result =
                        NftActivityElt(
                            activity[NFTActivityDTO.txHash],
                            activity[NFTActivityDTO.date],
                            "RARIBLE",
                            NftTransferActivityElt(
                                NftActivity.NFTActivityType.transfer,
                                NftBaseActivityElt(
                                    activity[NFTActivityDTO.to],
                                    activity[NFTActivityDTO.contract],
                                    activity[NFTActivityDTO.tokenId],
                                    BigDecimal(activity[NFTActivityDTO.value]),
                                    activity[NFTActivityDTO.txHash],
                                    activity[NFTActivityDTO.blockHash],
                                    activity[NFTActivityDTO.blockNumber]
                                ),
                                activity[NFTActivityDTO.from],
                            )
                        )

                }
            }
            return result
        }

        private fun applyFilters(query: Query, continuation: String?, size: Int?, sort: com.rarible.protocol.tezos.api.model.ActivitySort?): Query {
            if(continuation != null){
                val continuationArgs = continuation.split('_')
                if(continuationArgs.size != 2){
                    throw NFTActivityRepositoryException("Continuation pattern must be TIMESTAMP_HASH", 500)
                }
                val timestamp = continuationArgs[0].toLong()
                val hash = continuationArgs[1]
                if(sort != null && sort == com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST){
                    query.andWhere {
                        (NFTActivityDTO.txHash less  hash) and (NFTActivityDTO.date less Instant.ofEpochMilli(timestamp))
                    }
                } else {
                    query.andWhere {
                        (NFTActivityDTO.txHash greater hash) and (NFTActivityDTO.date greater Instant.ofEpochMilli(timestamp))
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
                if(sort == com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST){
                    query.orderBy(Pair(NFTActivityDTO.date, SortOrder.DESC), Pair(NFTActivityDTO.txHash, SortOrder.DESC))
                } else {
                    query.orderBy(Pair(NFTActivityDTO.date, SortOrder.ASC), Pair(NFTActivityDTO.txHash, SortOrder.ASC))
                }
            } else {
                query.orderBy(Pair(NFTActivityDTO.date, SortOrder.ASC), Pair(NFTActivityDTO.txHash, SortOrder.ASC))
            }

            return query
        }

        fun queryAllNFTActivities(types: List<NftActivityFilterAllType>, continuation: String?, size: Int?, sort: com.rarible.protocol.tezos.api.model.ActivitySort?): List<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            var query =  NFTActivityDTO.select{
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

        fun queryNFTActivitiesByUser(users: List<String>, types: List<NftActivityFilterUserType>, continuation: String?, size: Int?, sort: com.rarible.protocol.tezos.api.model.ActivitySort?): List<NftActivityElt> {
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
            var query =  NFTActivityDTO.select{
                (type inList requestTypes)
            }
            if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && !types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivityDTO.from inList users)}
            }
            else if(!types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivityDTO.to inList users) }
            }
            else if(types.contains(NftActivityFilterUserType.TRANSFERFROM) && types.contains(NftActivityFilterUserType.TRANSFERTO)){
                query.andWhere { (NFTActivityDTO.from inList users) or (NFTActivityDTO.to inList users) }
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

        fun queryNFTActivitiesByItem(contract: String, tokenId: String?, types: List<NftActivityFilterAllType>, continuation: String?, size: Int?, sort: com.rarible.protocol.tezos.api.model.ActivitySort?): List<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()

            var query =  NFTActivityDTO.select{
                (type inList types.map { it.value }) and (NFTActivityDTO.contract eq contract)
            }
            if(!tokenId.isNullOrEmpty()){
                query.andWhere { (NFTActivityDTO.tokenId eq tokenId)}
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
