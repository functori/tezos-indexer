package org.rarible.tezos.client.tzkt.repositories

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTActivities
import org.rarible.tezos.indexer.model.activities.NftActivity
import org.rarible.tezos.indexer.model.activities.NftActivityElt
import org.rarible.tezos.indexer.model.activities.NftBaseActivityElt
import org.rarible.tezos.indexer.model.activities.NftMintBurnActivityElt
import org.rarible.tezos.indexer.model.activities.NftTransferActivityElt
import java.math.BigDecimal
import java.time.Instant
import java.time.OffsetDateTime

class NFTActivityRepository {
    companion object {
        fun queryAllNFTActivities(types: List<String>): MutableList<NftActivityElt> {
            var result: MutableList<NftActivityElt> = mutableListOf()
            transaction {
                NFTActivities.select {
                    (NFTActivities.type inList types)
                }.limit(10).forEach {
                    println(it[NFTActivities.type])
                    when (it[NFTActivities.type]) {
                        NftActivity.NFTActivityType.mint.value -> {
                            result.add(
                                NftActivityElt(
                                    it[NFTActivities.txHash],
                                    it[NFTActivities.date],
                                    "RARIBLE",
                                    NftMintBurnActivityElt(
                                        NftActivity.NFTActivityType.mint,
                                        it[NFTActivities.to],
                                        it[NFTActivities.contract],
                                        it[NFTActivities.tokenId],
                                        BigDecimal(it[NFTActivities.value]),
                                        it[NFTActivities.txHash],
                                        it[NFTActivities.blockHash],
                                        it[NFTActivities.blockNumber]
                                    )
                                )
                            )
                        }
                        NftActivity.NFTActivityType.burn.value -> {
                            result.add(
                                NftActivityElt(
                                    it[NFTActivities.txHash],
                                    it[NFTActivities.date],
                                    "RARIBLE",
                                    NftMintBurnActivityElt(
                                        NftActivity.NFTActivityType.burn,
                                        it[NFTActivities.to],
                                        it[NFTActivities.contract],
                                        it[NFTActivities.tokenId],
                                        BigDecimal(it[NFTActivities.value]),
                                        it[NFTActivities.txHash],
                                        it[NFTActivities.blockHash],
                                        it[NFTActivities.blockNumber]
                                    )
                                )
                            )
                        }
                        NftActivity.NFTActivityType.transfer.value -> {
                            result.add(
                                NftActivityElt(
                                    it[NFTActivities.txHash],
                                    it[NFTActivities.date],
                                    "RARIBLE",
                                    NftTransferActivityElt(
                                        NftActivity.NFTActivityType.transfer,
                                        NftBaseActivityElt(
                                            it[NFTActivities.to],
                                            it[NFTActivities.contract],
                                            it[NFTActivities.tokenId],
                                            BigDecimal(it[NFTActivities.value]),
                                            it[NFTActivities.txHash],
                                            it[NFTActivities.blockHash],
                                            it[NFTActivities.blockNumber]
                                        ),
                                        it[NFTActivities.from],
                                    )
                                )
                            )
                        }
                    }

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