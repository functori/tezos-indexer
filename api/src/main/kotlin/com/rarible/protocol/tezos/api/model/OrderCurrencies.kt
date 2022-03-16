package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param orderType
 * @param currencies
 */
data class OrderCurrencies(

    @field:Valid
    @field:JsonProperty("order_type", required = true) val orderType: OrderType,

    @field:Valid
    @field:JsonProperty("currencies", required = true) val currencies: kotlin.collections.List<AssetType>
) {

}

