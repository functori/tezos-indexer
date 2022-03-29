package com.rarible.protocol.tezos.api.service


import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getCreator
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getRoyalties
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.nftItemOfToken
import org.springframework.stereotype.Component

@Component
class NftItemService(
    val tokensClient: TokensApi,
    val bigmapClient: BigMapsApi
) {

    suspend fun getById(itemId: String): NftItem {
        val list = itemId.split(':')
        val contract = list[0]
        val tokenId = list[1]
        val tokens = tokensClient.tokensGetTokens(contract, tokenId, limit = 1, offset = null, sort = null)
        val token = tokens[0]
        val royalties = getRoyalties(bigmapClient, contract, tokenId)
        val creator = getCreator(tokensClient, contract, tokenId, royalties)
        val tokenBalances = tokensClient.tokensGetTokenBalances(contract, tokenId, account = null)
        return nftItemOfToken(token, royalties, creator, tokenBalances)
    }

}