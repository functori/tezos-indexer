package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param eventId 
 * @param collectionId 
 * @param type 
 * @param collection 
 */
data class NftCollectionEvent(

    @field:JsonProperty("eventId", required = true) val eventId: kotlin.String,

    @field:JsonProperty("collectionId", required = true) val collectionId: kotlin.String,

    @field:JsonProperty("type", required = true) val type: NftCollectionEvent.Type,

    @field:Valid
    @field:JsonProperty("collection", required = true) val collection: NftCollection
) {

    /**
    * 
    * Values: uPDATE
    */
    enum class Type(val value: kotlin.String) {
    
        @JsonProperty("UPDATE") uPDATE("UPDATE");
    
    }

}

