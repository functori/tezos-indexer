package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.AssetType
import com.rarible.tezos.server.model.OrderType
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
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

