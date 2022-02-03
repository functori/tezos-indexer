package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param eventId 
 * @param ownershipId 
 * @param type 
 * @param ownership 
 */
data class NftOwnershipEvent(

    @field:JsonProperty("eventId", required = true) val eventId: kotlin.String,

    @field:JsonProperty("ownershipId", required = true) val ownershipId: kotlin.String,

    @field:JsonProperty("type", required = true) val type: NftOwnershipEvent.Type,

    @field:Valid
    @field:JsonProperty("ownership", required = true) val ownership: NftOwnership
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

