package com.rarible.protocol.tezos.api.service

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.model.items.NftOwnerships
import com.rarible.protocol.tezos.api.util.tzkt.api.models.IdParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.models.SortParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getCreator
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getRoyalties
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.nftOwnershipOfTokenBalance
import org.springframework.stereotype.Component
import kotlin.math.min

@Component
class NftOwnershipService(
    val tokensClient: TokensApi,
    val bigmapClient: BigMapsApi
) {

    fun makeNftOwnership(contract : String, tokenId : String, owner : String) : NftOwnership {
        val royalties = getRoyalties(bigmapClient, contract, tokenId, null)
        val creator = getCreator(tokensClient, contract, tokenId, royalties)
        val tokenBalances = tokensClient.tokensGetTokenBalances(contract, tokenId, owner, id = null, offset = null, limit = null, sort = null)
        val tokenBalance = tokenBalances[0]
        return nftOwnershipOfTokenBalance(contract, tokenId, creator, tokenBalance)
    }

    suspend fun getById(ownershipId: String): NftOwnership {
        val list = ownershipId.split(':')
        val contract = list[0]
        val tokenId = list[1]
        val owner = list[2]
        return makeNftOwnership(contract, tokenId, owner)
    }

    suspend fun getAll(size: Int?, continuation: String?) : NftOwnerships {
        var res = listOf<NftOwnership>()
        var cont = continuation
        while (res.size != size) {
            val limit = min(10000, size ?: 20)
            var tokensBalances = tokensClient.tokensGetTokenBalances(
                id = IdParameter(lt = continuation),
                sort = SortParameter(desc = "id"),
                limit = limit,
                offset = null,
                account = null,
                tokenPeriodContract = null,
                tokenPeriodTokenId = null
            )
            if (tokensBalances.isEmpty()) {
                cont = null
                break ;
            }
            else {
                val tbs = tokensBalances.map { makeNftOwnership(it.token?.contract?.address!!, it.token?.tokenId!!, it.account?.address!!) }
                cont = tbs.last().id.toString()
                res += tbs
            }
        }
        return NftOwnerships(ownerships = res, continuation = cont, total = 0)
    }


}