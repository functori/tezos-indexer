package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
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

    @field:JsonProperty("maker", required = true) val maker: String,

    @field:JsonProperty("makerEdpk", required = true) val makerEdpk: String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: com.rarible.protocol.tezos.api.model.Asset,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: com.rarible.protocol.tezos.api.model.Asset,

    @field:JsonProperty("salt", required = true) val salt: String,

    @field:JsonProperty("signature", required = true) val signature: String,

    @field:JsonProperty("type", required = true) val type: OrderForm.Type,

    @field:JsonProperty("taker") val taker: String? = null,

    @field:JsonProperty("takerEdpk") val takerEdpk: String? = null,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("start") val start: Int? = null,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("end") val end: Int? = null
) {

    /**
    *
    * Values: rARIBLEV2
    */
    enum class Type(val value: String) {

        @JsonProperty("RARIBLE_V2") rARIBLEV2("RARIBLE_V2");

    }

}

