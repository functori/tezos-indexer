package org.rarible.tezos.client.tzkt.repositories

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTActivities
import org.rarible.tezos.client.tzkt.models.TokenBalances
import org.rarible.tezos.client.tzkt.models.TokenBalances.contract
import org.rarible.tezos.client.tzkt.models.TokenBalances.tokenId
import org.rarible.tezos.indexer.model.NftActivityElt
import org.rarible.tezos.indexer.model.NftActivityFilterAllType
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

class NFTActivityRepository {
    companion object {
        fun queryAllNFTActivities(types: List<String>): MutableList<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()

            transaction {
                NFTActivities.select {
                    (NFTActivities.type inList types)
                }.limit(10).forEach {
                    result.add(
                        NftActivityElt(
                            it[NFTActivities.to],
                            it[NFTActivities.contract],
                            it[NFTActivities.tokenId],
                            BigDecimal(it[NFTActivities.value]),
                            it[NFTActivities.txHash],
                            it[NFTActivities.blockHash],
                            it[NFTActivities.blockNumber].toInt()
                        )
                    )
                }
            }
            return result
        }

        fun queryNFTActivitiesByUser(contract: String, owner: String, tokenId: String?) {

        }

        fun queryNFTActivitiesByItem(contract: String, owner: String, tokenId: String?) {

        }

        fun queryNFTActivitiesByCollection(contract: String, owner: String, tokenId: String?) {

        }
    }
}