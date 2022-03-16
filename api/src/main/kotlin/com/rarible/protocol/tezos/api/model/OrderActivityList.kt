package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
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
data class OrderActivityList(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityList.AtType,

    @field:JsonProperty("hash", required = true) val hash: String,

    @field:JsonProperty("maker", required = true) val maker: String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: com.rarible.protocol.tezos.api.model.Asset,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: com.rarible.protocol.tezos.api.model.Asset,

    @field:Valid
    @field:JsonProperty("price", required = true) val price: java.math.BigDecimal
) {

    /**
    *
    * Values: list
    */
    enum class AtType(val value: String) {

        @JsonProperty("list") list("list");

    }

}

