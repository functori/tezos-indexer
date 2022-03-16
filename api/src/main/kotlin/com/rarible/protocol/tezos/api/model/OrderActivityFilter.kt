package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param atType
 * @param types
 * @param users
 * @param contract
 * @param tokenId
 */
data class OrderActivityFilter(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityFilter.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<OrderActivityFilterAllType>,

    @field:JsonProperty("users", required = true) val users: kotlin.collections.List<String>,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String
) {

    /**
    *
    * Values: byCollection
    */
    enum class AtType(val value: String) {

        @JsonProperty("by_collection") byCollection("by_collection");

    }

}

