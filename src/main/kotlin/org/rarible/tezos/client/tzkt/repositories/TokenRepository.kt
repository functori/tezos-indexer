package org.rarible.tezos.client.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.repositories.model.TokenBalances
import org.rarible.tezos.client.tzkt.repositories.model.Tokens
import org.rarible.tezos.indexer.filters.GetFTBalanceFilter
import org.rarible.tezos.indexer.model.FTBalance
import java.math.BigDecimal

class TokenRepository {
    fun queryTokens(filter: String, pagination: String, fields: String) {
        transaction {
            val query = Tokens.selectAll().limit(10, offset = 1).forEach { println(it[Tokens.id]) }
        }
    }

    fun queryTokenBalances(filter: GetFTBalanceFilter, pagination: String, fields: String): List<FTBalance>? {
        val balances: MutableList<FTBalance> = mutableListOf()
        if(filter.tokenId.isNullOrEmpty()){
            transaction {
                TokenBalances.select{
                    (TokenBalances.owner eq filter.owner) and (TokenBalances.contract eq filter.contract)
                }.limit(10, offset = 0).forEach {
                    balances.plus(
                        FTBalance(it[TokenBalances.contract],it[TokenBalances.owner],
                            BigDecimal(it[TokenBalances.balance]))
                    )
                }
            }
        } else {
            transaction {
                TokenBalances.select{
                    (TokenBalances.owner eq filter.owner) and (TokenBalances.contract eq filter.contract) and (TokenBalances.tokenId eq filter.tokenId)
                }.limit(10, offset = 0).forEach {
                    balances.add(
                        FTBalance(it[TokenBalances.contract],it[TokenBalances.owner],
                            BigDecimal(it[TokenBalances.balance]))
                        )
                }
            }
        }
        return balances
    }
}