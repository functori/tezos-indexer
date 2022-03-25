package com.rarible.protocol.tezos.api.util.tzkt.api.utils

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.model.items.NftItemMeta
import com.rarible.protocol.tezos.api.util.tzkt.api.models.Token
import com.rarible.protocol.tezos.api.util.tzkt.api.models.TokenBalance

fun nftItemOfToken(token: Token, roy: Pair<List<Part>, Boolean>, creator : Part, tokenBalances : Array<TokenBalance>): NftItem {
    val contract = token.contract?.address!!
    val tokenId = token.tokenId!!
    val id = "$contract:$tokenId"
    val creators = listOf(creator)
    val supply = token.totalSupply!!
    val burned = token.totalBurned!!
    val owners = tokenBalances.map { it.account!!.address!! }.toList()
    val royalties = roy.first
    val date = token.lastTime!!
    val mintedAt = token.firstTime!!
    val deleted = (supply.toInt() - burned.toInt()) == 0
    val onchainRoyalties = roy.second
    var meta = NftItemMeta(name=token.metadata?.name!!.toString())
        return NftItem(
        id,
        contract,
        tokenId,
        creators,
        supply,
        lazySupply = "0",
        owners,
        royalties,
        date,
        mintedAt,
        deleted,
        onchainRoyalties,
        meta
    )
}

fun nftOwnershipOfTokenBalance(contract : String, tokenID : String, creator : Part, tokenBalance : TokenBalance) : NftOwnership {
    val owner = tokenBalance.account?.address!!
    val id = "$contract:$tokenID:$owner"
    val creators = listOf(creator)
    val date = tokenBalance.lastTime!!
    val createdAt = tokenBalance.firstTime!!
    val balance = tokenBalance.balance!!
    val lazyValue = "0"
    return NftOwnership(id,contract,tokenID,owner,creators,balance,lazyValue,date,createdAt)
}