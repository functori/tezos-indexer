package org.rarible.tezos.client.tzkt.repositories

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTActivities
import org.rarible.tezos.client.tzkt.models.NFTActivities.type
import org.rarible.tezos.client.tzkt.models.TokenBalances.owner
import org.rarible.tezos.indexer.model.activities.NftActivity
import org.rarible.tezos.indexer.model.activities.NftActivityElt
import org.rarible.tezos.indexer.model.activities.NftBaseActivityElt
import org.rarible.tezos.indexer.model.activities.NftMintBurnActivityElt
import org.rarible.tezos.indexer.model.activities.NftTransferActivityElt
import java.math.BigDecimal

class NFTActivityRepository {
    companion object {
        class NFTActivityRepositoryException(msg: String, val code: Int) : Exception(msg)

        fun processActivity(activity: ResultRow): NftActivityElt? {
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

        fun queryAllNFTActivities(types: List<String>): MutableList<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            transaction {
                NFTActivities.select {
                    (type inList types)
                }.limit(10).forEach {
                    var activity: NftActivityElt? =
                        processActivity(it) ?: throw NFTActivityRepositoryException("Could not parse activity: $it", 500)
                    result.add(activity!!)
                }
            }
            return result
        }

        fun queryNFTActivitiesByUser(users: List<String>, types: List<String>): MutableList<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            var requestTypes: MutableSet<String> = mutableSetOf()
            types.forEach{
                when(it){
                    NftActivity.NFTActivityType.transferFrom.value,NftActivity.NFTActivityType.transferTo.value ->{
                        requestTypes.add(NftActivity.NFTActivityType.transfer.value)
                    }
                    NftActivity.NFTActivityType.mint.value -> requestTypes.add(NftActivity.NFTActivityType.mint.value)
                    NftActivity.NFTActivityType.burn.value -> requestTypes.add(NftActivity.NFTActivityType.burn.value)
                }
            }
            var sqlExpression =  NFTActivities.select{
                (type inList requestTypes)
            }
            if(types.contains(NftActivity.NFTActivityType.transferFrom.value) && !types.contains(NftActivity.NFTActivityType.transferTo.value)){
                sqlExpression.andWhere { (NFTActivities.from inList users)}
            }
            else if(!types.contains(NftActivity.NFTActivityType.transferFrom.value) && types.contains(NftActivity.NFTActivityType.transferTo.value)){
                sqlExpression.andWhere { (NFTActivities.to inList users) }
            }
            else if(types.contains(NftActivity.NFTActivityType.transferFrom.value) && types.contains(NftActivity.NFTActivityType.transferTo.value)){
                sqlExpression.andWhere { (NFTActivities.from inList users) or (NFTActivities.to inList users) }
            }
            transaction {
                sqlExpression.limit(100).forEach {
                    var activity: NftActivityElt? =
                        processActivity(it) ?: throw NFTActivityRepositoryException("Could not parse activity: $it", 500)
                    result.add(activity!!)
                }
            }

            return result
        }

        fun queryNFTActivitiesByItem(contract: String, owner: String, tokenId: String?) {

        }

        fun queryNFTActivitiesByCollection(contract: String, owner: String, tokenId: String?) {

        }
    }
}