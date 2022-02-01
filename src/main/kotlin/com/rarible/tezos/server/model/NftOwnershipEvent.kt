package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.NftOwnership
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

