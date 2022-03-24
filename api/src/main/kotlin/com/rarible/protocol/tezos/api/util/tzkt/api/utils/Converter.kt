package com.rarible.protocol.tezos.api.util.tzkt.api.utils

import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.util.tzkt.api.models.Token
import com.rarible.protocol.tezos.api.util.tzkt.api.models.TokenBalance

object Converter {
    fun nftItemOfToken(token: Token, roy: Pair<List<Part>, Boolean>, creator : Part, tokenBalances : Array<TokenBalance>): NftItem {
        val contract = token.contract!!
        val tokenId = token.tokenId!!
        val id = contract + tokenId
        val creators = listOf(creator)
        val supply = token.totalSupply!!
        val burned = token.totalBurned!!
        val owners = tokenBalances.map { it.account!!.address!! }.toList<String>()
        val royalties = roy.first
        val date = token.lastTime!!.toInstant()
        val mintedAt = token.firstTime!!.toInstant()
        val deleted = (supply.toInt() - burned.toInt()) == 0
        val onchainRoyalties = roy.second
        return NftItem(id, contract, tokenId, creators, supply, lazySupply = "0", owners, royalties, date, mintedAt, deleted, onchainRoyalties)
    }

}