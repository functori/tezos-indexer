package org.rarible.tezos.indexer.api

import org.rarible.tezos.indexer.model.ActivitySort
import org.rarible.tezos.indexer.model.FTBalance
import org.rarible.tezos.indexer.model.InlineResponse200
import org.rarible.tezos.indexer.model.NftActivities
import org.rarible.tezos.indexer.model.NftActivityFilter
import org.rarible.tezos.indexer.model.NftCollection
import org.rarible.tezos.indexer.model.NftCollections
import org.rarible.tezos.indexer.model.NftItem
import org.rarible.tezos.indexer.model.NftItemMeta
import org.rarible.tezos.indexer.model.NftItemRoyalties
import org.rarible.tezos.indexer.model.NftItems
import org.rarible.tezos.indexer.model.NftOwnership
import org.rarible.tezos.indexer.model.NftOwnerships
import org.rarible.tezos.indexer.model.NftTokenId
import org.rarible.tezos.indexer.model.Order
import org.rarible.tezos.indexer.model.OrderActivities
import org.rarible.tezos.indexer.model.OrderActivityFilter
import org.rarible.tezos.indexer.model.OrderCurrencies
import org.rarible.tezos.indexer.model.OrderForm
import org.rarible.tezos.indexer.model.OrderIds
import org.rarible.tezos.indexer.model.OrderPagination
import org.rarible.tezos.indexer.model.OrderSort
import org.rarible.tezos.indexer.model.OrderStatus
import org.rarible.tezos.indexer.model.SignatureValidationForm
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
    fun ftBalance( @PathVariable("contract") contract: kotlin.String
, @PathVariable("owner") owner: kotlin.String
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
): ResponseEntity<FTBalance> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/{collection}/generate_token_id"],
        produces = ["application/json"]
    )
    fun generateNftTokenId( @PathVariable("collection") collection: kotlin.String
): ResponseEntity<NftTokenId> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/order/orders/currencies/byBidOrdersOfItem"],
        produces = ["application/json"]
    )
    fun getCurrenciesByBidOrdersOfItem( @RequestParam(value = "contract", required = false) contract: kotlin.String?
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
): ResponseEntity<OrderCurrencies> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/order/orders/currencies/bySellOrdersOfItem"],
        produces = ["application/json"]
    )
    fun getCurrenciesBySellOrdersOfItem( @RequestParam(value = "contract", required = false) contract: kotlin.String?
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
): ResponseEntity<OrderCurrencies> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/nft/activities/search"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getNftActivities( @RequestParam(value = "sort", required = false) sort: ActivitySort?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
, @Valid @RequestBody(required = false) nftActivityFilter: NftActivityFilter?
): ResponseEntity<NftActivities> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/all"],
        produces = ["application/json"]
    )
    fun getNftAllItems( @RequestParam(value = "lastUpdateFrom", required = false) lastUpdateFrom: kotlin.String?
, @RequestParam(value = "lastUpdateTo", required = false) lastUpdateTo: kotlin.String?
, @RequestParam(value = "showDeleted", required = false) showDeleted: kotlin.Boolean?
, @RequestParam(value = "includeMeta", required = false) includeMeta: kotlin.Boolean?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftItems> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/all"],
        produces = ["application/json"]
    )
    fun getNftAllOwnerships(@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftOwnerships> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/{collection}"],
        produces = ["application/json"]
    )
    fun getNftCollectionById( @PathVariable("collection") collection: kotlin.String
): ResponseEntity<NftCollection> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}"],
        produces = ["application/json"]
    )
    fun getNftItemById( @PathVariable("itemId") itemId: kotlin.String
, @RequestParam(value = "includeMeta", required = false) includeMeta: kotlin.Boolean?
): ResponseEntity<NftItem> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}/meta"],
        produces = ["application/json"]
    )
    fun getNftItemMetaById( @PathVariable("itemId") itemId: kotlin.String
): ResponseEntity<NftItemMeta> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/{itemId}/royalties"],
        produces = ["application/json"]
    )
    fun getNftItemRoyalties( @PathVariable("itemId") itemId: kotlin.String
): ResponseEntity<NftItemRoyalties> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byCollection"],
        produces = ["application/json"]
    )
    fun getNftItemsByCollection( @RequestParam(value = "collection", required = false) collection: kotlin.String?
, @RequestParam(value = "includeMeta", required = false) includeMeta: kotlin.Boolean?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftItems> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byCreator"],
        produces = ["application/json"]
    )
    fun getNftItemsByCreator( @RequestParam(value = "creator", required = false) creator: kotlin.String?
, @RequestParam(value = "includeMeta", required = false) includeMeta: kotlin.Boolean?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftItems> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/items/byOwner"],
        produces = ["application/json"]
    )
    fun getNftItemsByOwner( @RequestParam(value = "owner", required = false) owner: kotlin.String?
, @RequestParam(value = "includeMeta", required = false) includeMeta: kotlin.Boolean?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftItems> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/{ownershipId}"],
        produces = ["application/json"]
    )
    fun getNftOwnershipById( @PathVariable("ownershipId") ownershipId: kotlin.String
): ResponseEntity<NftOwnership> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/ownerships/byItem"],
        produces = ["application/json"]
    )
    fun getNftOwnershipByItem( @RequestParam(value = "contract", required = false) contract: kotlin.String?
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftOwnerships> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/order/activities/search"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getOrderActivities( @RequestParam(value = "sort", required = false) sort: ActivitySort?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
, @Valid @RequestBody(required = false) orderActivityFilter: OrderActivityFilter?
): ResponseEntity<OrderActivities> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/bids/byItem"],
        produces = ["application/json"]
    )
    fun getOrderBidsByItem( @RequestParam(value = "contract", required = false) contract: kotlin.String?
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
, @RequestParam(value = "maker", required = false) maker: kotlin.String?
, @RequestParam(value = "origin", required = false) origin: kotlin.String?
, @RequestParam(value = "currencyId", required = false) currencyId: kotlin.String?
, @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "startDate", required = false) startDate: kotlin.Int?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "endDate", required = false) endDate: kotlin.Int?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/bids/byMaker"],
        produces = ["application/json"]
    )
    fun getOrderBidsByMaker( @RequestParam(value = "maker", required = false) maker: kotlin.String?
, @RequestParam(value = "origin", required = false) origin: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/{hash}"],
        produces = ["application/json"]
    )
    fun getOrderByHash( @PathVariable("hash") hash: kotlin.String
): ResponseEntity<Order> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/orders/byIds"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun getOrderByIds( @Valid @RequestBody(required = false) orderIds: OrderIds?
): ResponseEntity<List<Order>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/all"],
        produces = ["application/json"]
    )
    fun getOrdersAll( @RequestParam(value = "origin", required = false) origin: kotlin.String?
, @RequestParam(value = "sort", required = false) sort: OrderSort?
, @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byItem"],
        produces = ["application/json"]
    )
    fun getSellOrderByItem( @RequestParam(value = "contract", required = false) contract: kotlin.String?
, @RequestParam(value = "tokenId", required = false) tokenId: kotlin.String?
, @RequestParam(value = "maker", required = false) maker: kotlin.String?
, @RequestParam(value = "origin", required = false) origin: kotlin.String?
, @RequestParam(value = "currencyId", required = false) currencyId: kotlin.String?
, @RequestParam(value = "status", required = false) status: kotlin.collections.List<OrderStatus>?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "startDate", required = false) startDate: kotlin.Int?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "endDate", required = false) endDate: kotlin.Int?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell"],
        produces = ["application/json"]
    )
    fun getSellOrders( @RequestParam(value = "origin", required = false) origin: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byCollection"],
        produces = ["application/json"]
    )
    fun getSellOrdersByCollection( @RequestParam(value = "collection", required = false) collection: kotlin.String?
, @RequestParam(value = "origin", required = false) origin: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/orders/sell/byMaker"],
        produces = ["application/json"]
    )
    fun getSellOrdersByMaker( @RequestParam(value = "maker", required = false) maker: kotlin.String?
, @RequestParam(value = "origin", required = false) origin: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<OrderPagination> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/v0.1/items/{itemId}/reset"],
        produces = ["application/json"]
    )
    fun resetNftItemMetaById( @PathVariable("itemId") itemId: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/all"],
        produces = ["application/json"]
    )
    fun searchNftAllCollections(@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
): ResponseEntity<NftCollections> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v0.1/collections/byOwner"],
        produces = ["application/json"]
    )
    fun searchNftCollectionsByOwner( @RequestParam(value = "owner", required = false) owner: kotlin.String?
,@Min(0) @Max(9007199254740992)  @RequestParam(value = "size", required = false) size: kotlin.Int?
, @RequestParam(value = "continuation", required = false) continuation: kotlin.String?
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
    fun upsertOrder( @Valid @RequestBody(required = false) orderForm: OrderForm?
): ResponseEntity<Order> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v0.1/order/signature/validate"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun validate( @Valid @RequestBody(required = false) signatureValidationForm: SignatureValidationForm?
): ResponseEntity<kotlin.Boolean> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
