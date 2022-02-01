package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.NftCollection
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
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

