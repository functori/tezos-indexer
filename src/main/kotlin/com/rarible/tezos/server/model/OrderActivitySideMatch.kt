package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.Asset
import com.rarible.tezos.server.model.OrderActivitySideType
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
 * @param maker 
 * @param hash 
 * @param asset 
 * @param type 
 */
data class OrderActivitySideMatch(

    @field:JsonProperty("maker", required = true) val maker: kotlin.String,

    @field:JsonProperty("hash", required = true) val hash: kotlin.String,

    @field:Valid
    @field:JsonProperty("asset", required = true) val asset: Asset,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivitySideType
) {

}

