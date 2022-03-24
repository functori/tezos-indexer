package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.model.*
import com.rarible.protocol.tezos.api.util.tzkt.repositories.NFTActivityRepository
import com.rarible.protocol.tezos.api.util.tzkt.repositories.NFTItemsRepository
import com.rarible.protocol.tezos.api.util.tzkt.repositories.TokenRepository
import org.rarible.tezos.indexer.api.InternalErrorException
import org.rarible.tezos.indexer.api.NotFoundException
import com.rarible.protocol.tezos.api.model.activities.NftActivities
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilter
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterAll
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByCollection
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByItem
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByUser
import com.rarible.protocol.tezos.api.model.collections.NftCollection
import com.rarible.protocol.tezos.api.model.collections.NftCollections
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.model.items.NftItemMeta
import com.rarible.protocol.tezos.api.model.items.NftItemRoyalties
import com.rarible.protocol.tezos.api.model.items.NftItems
import com.rarible.protocol.tezos.api.util.tzkt.api.models.TokenBalance
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class V01ApiController() {

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/balances/{contract}/{owner}"],
        produces = ["application/json"]
    )
    fun ftBalance(
        @PathVariable("contract") contract: String,
        @PathVariable("owner") owner: String,
        @RequestParam(value = "tokenId", required = false) tokenId: String?
    ): ResponseEntity<FTBalance> {
        var balance: FTBalance?
        var tokenExists = TokenRepository.checkTokenExists(contract, tokenId)
        if (!tokenExists) {
            throw NotFoundException("TOKEN_NOT_FOUND")
        }
        try {
            balance = TokenRepository.queryTokenBalances(contract, owner, tokenId)
        } catch (e: Exception) {
            throw InternalErrorException(e.message!!)
        }
        return ResponseEntity(balance, HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/{collection}/generate_token_id"],
        produces = ["application/json"]
    )
    fun generateNftTokenId(
        @PathVariable("collection") collection: String
    ): ResponseEntity<NftTokenId> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/order/orders/currencies/byBidOrdersOfItem"],
        produces = ["application/json"]
    )
    fun getCurrenciesByBidOrdersOfItem(
        @RequestParam(value = "contract", required = false) contract: String?,
        @RequestParam(value = "tokenId", required = false) tokenId: String?
    ): ResponseEntity<OrderCurrencies> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/order/orders/currencies/bySellOrdersOfItem"],
        produces = ["application/json"]
    )
    fun getCurrenciesBySellOrdersOfItem(
        @RequestParam(value = "contract", required = false) contract: String?,
        @RequestParam(value = "tokenId", required = false) tokenId: String?
    ): ResponseEntity<OrderCurrencies> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/nft/activities/search"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getNftActivities(
        @RequestParam(value = "sort", required = false) sort: com.rarible.protocol.tezos.api.model.ActivitySort?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?,
        @Valid @RequestBody(required = false) nftActivityFilter: NftActivityFilter?
    ): ResponseEntity<NftActivities> {
        var activities = NftActivities(emptyList())

        when (nftActivityFilter) {
            is NftActivityFilterAll -> {
                activities.items =
                    NFTActivityRepository.queryAllNFTActivities(nftActivityFilter.types, continuation, size, sort)
            }
            is NftActivityFilterByCollection -> {
                activities.items = NFTActivityRepository.queryNFTActivitiesByItem(
                    nftActivityFilter.contract,
                    null,
                    nftActivityFilter.types,
                    continuation,
                    size,
                    sort
                )

            }
            is NftActivityFilterByItem -> {
                activities.items = NFTActivityRepository.queryNFTActivitiesByItem(
                    nftActivityFilter.contract,
                    nftActivityFilter.tokenId,
                    nftActivityFilter.types,
                    continuation,
                    size,
                    sort
                )

            }
            is NftActivityFilterByUser -> {
                activities.items = NFTActivityRepository.queryNFTActivitiesByUser(
                    nftActivityFilter.users,
                    nftActivityFilter.types,
                    continuation,
                    size,
                    sort
                )
            }
        }
        activities.continuation = "${activities.items.last().date}_${activities.items.last().id}"
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/all"],
        produces = ["application/json"]
    )
    fun getNftAllItems(
        @RequestParam(value = "lastUpdateFrom", required = false) lastUpdateFrom: String?,
        @RequestParam(value = "lastUpdateTo", required = false) lastUpdateTo: String?,
        @RequestParam(value = "showDeleted", required = false) showDeleted: Boolean?,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftItems> {
        val items = NFTItemsRepository.queryAllNFTItems(
            lastUpdateFrom,
            lastUpdateTo,
            showDeleted,
            includeMeta,
            size,
            continuation
        )
        return ResponseEntity(NftItems(items.size, items, "${items.last().date}_${items.last().id}"), HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/all"],
        produces = ["application/json"]
    )
    fun getNftAllOwnerships(
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftOwnerships> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/{collection}"],
        produces = ["application/json"]
    )
    fun getNftCollectionById(
        @PathVariable("collection") collection: String
    ): ResponseEntity<NftCollection> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}"],
        produces = ["application/json"]
    )
    fun getNftItemById(
        @PathVariable("itemId") itemId: String,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?
    ): ResponseEntity<NftItem> {
        val item = NFTItemsRepository.queryNFTItem(itemId, includeMeta)
        return ResponseEntity(item, HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items2/{itemId}"],
        produces = ["application/json"]
    )
    fun getNftItemById2(
        @PathVariable("itemId") itemId: String,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?
    ): ResponseEntity<NftItem> {
        val tApi = TokensApi()
        val bApi = BigMapsApi()
        val list = itemId.split(':')
        val contract = list[0]
        val tokenId = list[1]
        val tokens = tApi.tokensGetTokens(contract, tokenId, limit = 1, offset = null, sort = null)
        val token = tokens[0]
        val royalties = getRoyalties(bApi, contract, tokenId)
        println(royalties)
        val creator = getCreator(tApi, contract, tokenId, royalties)
        println(royalties)
        val tokenBalances = tApi.tokensGetTokenBalances(contract, tokenId)
        println(royalties)
        val item = nftItemOfToken(token, royalties, creator, tokenBalances)
        return ResponseEntity(item, HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}/meta"],
        produces = ["application/json"]
    )
    fun getNftItemMetaById(
        @PathVariable("itemId") itemId: String
    ): ResponseEntity<NftItemMeta> {
        val item = NFTItemsRepository.queryNFTItemMeta(itemId)
        return ResponseEntity(item, HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}/royalties"],
        produces = ["application/json"]
    )
    fun getNftItemRoyalties(
        @PathVariable("itemId") itemId: String
    ): ResponseEntity<NftItemRoyalties> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byCollection"],
        produces = ["application/json"]
    )
    fun getNftItemsByCollection(
        @RequestParam(value = "collection", required = true) collection: String,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftItems> {
        val items = NFTItemsRepository.queryNFTItemsByCollection(collection, includeMeta, size, continuation);
        return ResponseEntity(NftItems(items.size, items, "${items.last().date}_${items.last().id}"), HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byCreator"],
        produces = ["application/json"]
    )
    fun getNftItemsByCreator(
        @RequestParam(value = "creator", required = false) creator: String,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftItems> {
        val items = NFTItemsRepository.queryNFTItemsByCreator(creator, includeMeta, size, continuation);
        return ResponseEntity(NftItems(items.size, items, "${items.last().date}_${items.last().id}"), HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byOwner"],
        produces = ["application/json"]
    )
    fun getNftItemsByOwner(
        @RequestParam(value = "owner", required = false) owner: String,
        @RequestParam(value = "includeMeta", required = false) includeMeta: Boolean?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftItems> {
        val items = NFTItemsRepository.queryNFTItemsByOwner(owner, includeMeta, size, continuation);
        return ResponseEntity(NftItems(items.size, items, "${items.last().date}_${items.last().id}"), HttpStatus.OK)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/{ownershipId}"],
        produces = ["application/json"]
    )
    fun getNftOwnershipById(
        @PathVariable("ownershipId") ownershipId: String
    ): ResponseEntity<NftOwnership> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/byItem"],
        produces = ["application/json"]
    )
    fun getNftOwnershipByItem(
        @RequestParam(value = "contract", required = false) contract: String?,
        @RequestParam(value = "tokenId", required = false) tokenId: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftOwnerships> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/order/activities/search"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getOrderActivities(
        @RequestParam(value = "sort", required = false) sort: com.rarible.protocol.tezos.api.model.ActivitySort?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?,
        @Valid @RequestBody(required = false) orderActivityFilter: OrderActivityFilter?
    ): ResponseEntity<OrderActivities> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/bids/byItem"],
        produces = ["application/json"]
    )
    fun getOrderBidsByItem(
        @RequestParam(value = "contract", required = false) contract: String?,
        @RequestParam(value = "tokenId", required = false) tokenId: String?,
        @RequestParam(value = "maker", required = false) maker: String?,
        @RequestParam(value = "origin", required = false) origin: String?,
        @RequestParam(value = "currencyId", required = false) currencyId: String?,
        @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "startDate", required = false) startDate: Int?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "endDate", required = false) endDate: Int?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/bids/byMaker"],
        produces = ["application/json"]
    )
    fun getOrderBidsByMaker(
        @RequestParam(value = "maker", required = false) maker: String?,
        @RequestParam(value = "origin", required = false) origin: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/{hash}"],
        produces = ["application/json"]
    )
    fun getOrderByHash(
        @PathVariable("hash") hash: String
    ): ResponseEntity<Order> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/orders/byIds"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getOrderByIds(
        @Valid @RequestBody(required = false) orderIds: OrderIds?
    ): ResponseEntity<List<Order>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/all"],
        produces = ["application/json"]
    )
    fun getOrdersAll(
        @RequestParam(value = "origin", required = false) origin: String?,
        @RequestParam(value = "sort", required = false) sort: OrderSort?,
        @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byItem"],
        produces = ["application/json"]
    )
    fun getSellOrderByItem(
        @RequestParam(value = "contract", required = false) contract: String?,
        @RequestParam(value = "tokenId", required = false) tokenId: String?,
        @RequestParam(value = "maker", required = false) maker: String?,
        @RequestParam(value = "origin", required = false) origin: String?,
        @RequestParam(value = "currencyId", required = false) currencyId: String?,
        @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "startDate", required = false) startDate: Int?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "endDate", required = false) endDate: Int?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell"],
        produces = ["application/json"]
    )
    fun getSellOrders(
        @RequestParam(value = "origin", required = false) origin: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byCollection"],
        produces = ["application/json"]
    )
    fun getSellOrdersByCollection(
        @RequestParam(value = "collection", required = false) collection: String?,
        @RequestParam(value = "origin", required = false) origin: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byMaker"],
        produces = ["application/json"]
    )
    fun getSellOrdersByMaker(
        @RequestParam(value = "maker", required = false) maker: String?,
        @RequestParam(value = "origin", required = false) origin: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/v0.1/items/{itemId}/reset"],
        produces = ["application/json"]
    )
    fun resetNftItemMetaById(
        @PathVariable("itemId") itemId: String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/all"],
        produces = ["application/json"]
    )
    fun searchNftAllCollections(
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftCollections> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/byOwner"],
        produces = ["application/json"]
    )
    fun searchNftCollectionsByOwner(
        @RequestParam(value = "owner", required = false) owner: String?,
        @Min(0) @Max(9007199254740992) @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "continuation", required = false) continuation: String?
    ): ResponseEntity<NftCollections> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/status"],
        produces = ["application/json"]
    )
    fun status(): ResponseEntity<InlineResponse200> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/orders"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun upsertOrder(
        @Valid @RequestBody(required = false) orderForm: OrderForm?
    ): ResponseEntity<Order> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/order/signature/validate"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun validate(
        @Valid @RequestBody(required = false) signatureValidationForm: SignatureValidationForm?
    ): ResponseEntity<Boolean> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
