package com.rarible.protocol.tezos.api.util.tzkt.api.utils

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.model.items.NftItemMeta
import com.rarible.protocol.tezos.api.model.items.NftItems
import com.rarible.protocol.tezos.api.model.items.NftOwnerships
import com.rarible.protocol.tezos.api.util.tzkt.api.models.Token
import com.rarible.protocol.tezos.api.util.tzkt.api.models.TokenBalance
import com.rarible.protocol.tezos.dto.*

fun nftItemMetaDtoOfNftItemMeta(meta : NftItemMeta?) : NftItemMetaDto? {
    if (meta == null) return null
    else {
        val name = meta.name
        val animation = null
        val attributes = listOf<NftItemAttributeDto>()
        val description = meta.description
        val image = meta.image
        return NftItemMetaDto(name,animation,attributes,description, image)
    }
}

fun nftItemDtoOfNftItem(item: NftItem) : NftItemDto {
    return NftItemDto(
        id = item.id,
        contract = item.contract,
        tokenId = item.tokenId.toBigInteger(),
        creators = item.creators.map{PartDto(account=it.account, value=it.value)},
        date = item.date.toInstant(),
        mintedAt = item.mintedAt.toInstant(),
        deleted = item.deleted,
        supply = item.supply.toBigInteger(),
        lazySupply = item.lazySupply.toBigInteger(),
        owners = item.owners,
        royalties = item.royalties.map{PartDto(account=it.account, value=it.value)},
        meta = nftItemMetaDtoOfNftItemMeta(item.meta)
    )
}

fun nftItemsDtoOfNftItems(items: NftItems) : NftItemsDto {
    return NftItemsDto(
        items = items.items.map { nftItemDtoOfNftItem( it ) },
        total = items.total,
        continuation = items.continuation
    )
}

fun nftItemOfToken(token: Token, roy: Pair<List<Part>, Boolean>, creator : Part, tokenBalances : Array<TokenBalance>, includeMeta : Boolean?) : NftItem {
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
    val meta =
        if (includeMeta == true) NftItemMeta(name=token?.metadata?.name.toString()) else null
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