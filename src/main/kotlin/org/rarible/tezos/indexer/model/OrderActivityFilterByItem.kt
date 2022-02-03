package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 * @param contract 
 * @param tokenId 
 */
data class OrderActivityFilterByItem(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityFilterByItem.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<OrderActivityFilterAllType>,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String
) {

    /**
    * 
    * Values: byItem
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("by_item") byItem("by_item");
    
    }

}

