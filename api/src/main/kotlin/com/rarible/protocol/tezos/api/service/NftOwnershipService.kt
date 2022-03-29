package com.rarible.protocol.tezos.api.service

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getCreator
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.getRoyalties
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.nftOwnershipOfTokenBalance
import org.springframework.stereotype.Component

@Component
class NftOwnershipService(
    val tokensClient: TokensApi,
    val bigmapClient: BigMapsApi
) {

    suspend fun getById(ownershipId: String): NftOwnership {
        val list = ownershipId.split(':')
        val contract = list[0]
        val tokenId = list[1]
        val owner = list[2]
        val royalties = getRoyalties(bigmapClient, contract, tokenId)
        val creator = getCreator(tokensClient, contract, tokenId, royalties)
        val tokenBalances = tokensClient.tokensGetTokenBalances(contract, tokenId, owner)
        val tokenBalance = tokenBalances[0]
        return nftOwnershipOfTokenBalance(contract, tokenId, creator, tokenBalance)
    }

}