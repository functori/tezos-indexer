package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.Valid

/**
 *
 * @param atType
 * @param left
 * @param right
 * @param type
 * @param price
 * @param transactionHash
 * @param blockHash
 * @param blockNumber
 * @param logIndex
 * @param hash
 * @param maker
 * @param make
 * @param take
 */
data class OrderActivityType(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityType.AtType,

    @field:Valid
    @field:JsonProperty("left", required = true) val left: OrderActivitySideMatch,

    @field:Valid
    @field:JsonProperty("right", required = true) val right: OrderActivitySideMatch,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivityMatchType,

    @field:Valid
    @field:JsonProperty("price", required = true) val price: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) val transactionHash: String,

    @field:JsonProperty("blockHash", required = true) val blockHash: String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) val blockNumber: Int,

    @get:Min(-1073741824)
    @get:Max(1073741823)
    @field:JsonProperty("logIndex", required = true) val logIndex: Int,

    @field:JsonProperty("hash", required = true) val hash: String,

    @field:JsonProperty("maker", required = true) val maker: String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: AssetType,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: AssetType
) {

    /**
    *
    * Values: cancelList
    */
    enum class AtType(val value: String) {

        @JsonProperty("cancel_list") cancelList("cancel_list");

    }

}

