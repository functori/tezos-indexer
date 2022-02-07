package org.rarible.tezos.client.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.TokenBalances
import org.rarible.tezos.client.tzkt.models.TokenBalances.balance
import org.rarible.tezos.indexer.model.FTBalance
import java.math.BigDecimal

class TokenRepository {
    companion object {
        fun queryTokenBalances(contract: String, owner: String, tokenId: String?): FTBalance? {
            var balance: FTBalance? = null
            var result: Query? = null
            transaction {
                result = if (tokenId.isNullOrEmpty()) {
                    TokenBalances.select {
                        (TokenBalances.owner eq owner) and (TokenBalances.contract eq contract)
                    }
                } else {
                    TokenBalances.select {
                        (TokenBalances.owner eq owner) and (TokenBalances.contract eq contract) and (TokenBalances.tokenId eq tokenId)
                    }
                }

                if (result != null && result!!.count() > 0) {
                    val balanceResult = result!!.first()
                    balance = FTBalance(
                        balanceResult[TokenBalances.contract], balanceResult[TokenBalances.owner],
                        BigDecimal(balanceResult[TokenBalances.balance])
                    )
                }
            }
            return balance
        }
    }
}