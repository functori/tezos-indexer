package org.rarible.tezos.indexer.model

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

    @field:JsonProperty("contract", required = true) val contract: kotlin.String
) {

    /**
    * 
    * Values: byCollection
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("by_collection") byCollection("by_collection");
    
    }

}

