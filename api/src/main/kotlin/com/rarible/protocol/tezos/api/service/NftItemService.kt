package com.rarible.protocol.tezos.api.service


import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.model.items.NftItems
import com.rarible.protocol.tezos.api.model.items.NftOwnerships
import com.rarible.protocol.tezos.api.util.tzkt.api.models.IdParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.models.SortParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.models.Token
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getCreator
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getRoyalties
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.nftItemOfToken
import org.springframework.stereotype.Component
import kotlin.math.min

@Component
class NftItemService(
    val tokensClient: TokensApi,
    val bigmapClient: BigMapsApi
) {

    fun makeNftItem(token : Token, includeMeta: Boolean?) : NftItem{
        val contract = token.contract!!.address!!
        val tokenId = token.tokenId!!
        val royalties = getRoyalties(bigmapClient, contract, tokenId, token.metadata)
        val creator = getCreator(tokensClient, contract, tokenId, royalties)
        val tokenBalances =
            tokensClient.tokensGetTokenBalances(contract, tokenId, account = null, id = null, sort = null, offset = null, limit = null)
        return nftItemOfToken(token, royalties, creator, tokenBalances, includeMeta)
    }

    suspend fun getById(itemId: String, includeMeta: Boolean?): NftItem {
        val list = itemId.split(':')
        val contract = list[0]
        val tokenId = list[1]
        val tokens = tokensClient.tokensGetTokens(id = null, contract, tokenId, limit = 1, offset = null, sort = null)
        return makeNftItem(tokens[0], includeMeta)
    }

    suspend fun  getAll(
        showDeleted: Boolean?,
        includeMeta: Boolean?,
        size: Int?,
        continuation: String?) : NftItems {
        var res = listOf<NftItem>()
        var cont = continuation
        while (res.size != size) {
            val limit = min(10000, size ?: 20)
            var tokens = tokensClient.tokensGetTokens(
                id = IdParameter(lt = continuation),
                sort = SortParameter(desc = "id"),
                limit = limit,
                contract = null,
                tokenId = null,
                offset = null,
            )
            if (tokens.isEmpty()) {
                cont = null
                break ;
            }
            else {
                tokens = tokens.filter { it.totalSupply?.toInt() != 0 || showDeleted == true }.toTypedArray()
                val items = tokens.map { makeNftItem(it, includeMeta) }
                cont = tokens.last().id.toString()
                res += items
            }
        }
        return NftItems(items = res, continuation = cont, total = 0)
    }

}