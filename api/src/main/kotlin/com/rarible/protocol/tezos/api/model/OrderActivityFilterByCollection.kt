package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param atType
 * @param types
 * @param contract
 */
data class OrderActivityFilterByCollection(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityFilterByCollection.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<OrderActivityFilterAllType>,

    @field:JsonProperty("contract", required = true) val contract: String
) {

    /**
    *
    * Values: byCollection
    */
    enum class AtType(val value: String) {

        @JsonProperty("by_collection") byCollection("by_collection");

    }

}

