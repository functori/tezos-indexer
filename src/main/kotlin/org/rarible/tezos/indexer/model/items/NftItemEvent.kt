package org.rarible.tezos.indexer.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param eventId 
 * @param itemId 
 * @param type 
 * @param item 
 */
data class NftItemEvent(

    @field:JsonProperty("eventId", required = true) val eventId: kotlin.String,

    @field:JsonProperty("itemId", required = true) val itemId: kotlin.String,

    @field:JsonProperty("type", required = true) val type: Type,

    @field:Valid
    @field:JsonProperty("item", required = true) val item: NftItem
) {

    /**
    * 
    * Values: uPDATE,dELETE
    */
    enum class Type(val value: kotlin.String) {
    
        @JsonProperty("UPDATE") uPDATE("UPDATE"),
    
        @JsonProperty("DELETE") dELETE("DELETE");
    
    }

}

