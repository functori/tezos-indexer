package com.rarible.protocol.tezos.api.util.tzkt.repositories

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import com.rarible.protocol.tezos.api.util.tzkt.models.TokenBalanceDTO
import com.rarible.protocol.tezos.api.model.FTBalance
import java.math.BigDecimal

class TokenRepository {
    companion object {
        fun queryTokenBalances(contract: String, owner: String, tokenId: String?): FTBalance? {
            var balance: FTBalance? = null
            var result: Query?
            transaction {
                result = if (tokenId.isNullOrEmpty()) {
                    TokenBalanceDTO.select {
                        (TokenBalanceDTO.owner eq owner) and (TokenBalanceDTO.contract eq contract)
                    }
                } else {
                    TokenBalanceDTO.select {
                        (TokenBalanceDTO.owner eq owner) and (TokenBalanceDTO.contract eq contract) and (TokenBalanceDTO.tokenId eq tokenId)
                    }
                }

                balance = if (result != null && result!!.count() > 0) {
                    val balanceResult = result!!.first()
                    FTBalance(
                        balanceResult[TokenBalanceDTO.contract], balanceResult[TokenBalanceDTO.owner],
                        BigDecimal(balanceResult[TokenBalanceDTO.balance])
                    )
                } else {
                    FTBalance(contract, owner, BigDecimal(0))
                }
            }
            return balance
        }

        fun checkTokenExists(contract: String, tokenId: String?): Boolean {
            var result: Query?
            var found = false
            var queryTokenId = tokenId
            if(tokenId.isNullOrEmpty()){
                queryTokenId = "0"
            }
            transaction {
                result = TokenBalanceDTO.select {
                   (TokenBalanceDTO.contract eq contract) and (TokenBalanceDTO.tokenId eq queryTokenId.toString())
                }
                found = result != null && result!!.count() > 0
            }


            return found
        }
    }
}
