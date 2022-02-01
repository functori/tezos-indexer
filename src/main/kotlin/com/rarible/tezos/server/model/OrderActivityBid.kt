package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.Asset
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
 * @param atType 
 * @param hash 
 * @param maker 
 * @param make 
 * @param take 
 * @param price 
 */
data class OrderActivityBid(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityBid.AtType,

    @field:JsonProperty("hash", required = true) val hash: kotlin.String,

    @field:JsonProperty("maker", required = true) val maker: kotlin.String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: Asset,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: Asset,

    @field:Valid
    @field:JsonProperty("price", required = true) val price: java.math.BigDecimal
) {

    /**
    * 
    * Values: bid
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("bid") bid("bid");
    
    }

}

