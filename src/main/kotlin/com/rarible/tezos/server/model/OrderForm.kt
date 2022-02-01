package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.Asset
import com.rarible.tezos.server.model.OrderRaribleV2DataV1
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
 * @param &#x60;data&#x60; 
 * @param maker 
 * @param makerEdpk 
 * @param make 
 * @param take 
 * @param salt 
 * @param signature 
 * @param type 
 * @param taker 
 * @param takerEdpk 
 * @param start 
 * @param end 
 */
data class OrderForm(

    @field:Valid
    @field:JsonProperty("data", required = true) val `data`: OrderRaribleV2DataV1,

    @field:JsonProperty("maker", required = true) val maker: kotlin.String,

    @field:JsonProperty("makerEdpk", required = true) val makerEdpk: kotlin.String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: Asset,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: Asset,

    @field:JsonProperty("salt", required = true) val salt: kotlin.String,

    @field:JsonProperty("signature", required = true) val signature: kotlin.String,

    @field:JsonProperty("type", required = true) val type: OrderForm.Type,

    @field:JsonProperty("taker") val taker: kotlin.String? = null,

    @field:JsonProperty("takerEdpk") val takerEdpk: kotlin.String? = null,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("start") val start: kotlin.Int? = null,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("end") val end: kotlin.Int? = null
) {

    /**
    * 
    * Values: rARIBLEV2
    */
    enum class Type(val value: kotlin.String) {
    
        @JsonProperty("RARIBLE_V2") rARIBLEV2("RARIBLE_V2");
    
    }

}

