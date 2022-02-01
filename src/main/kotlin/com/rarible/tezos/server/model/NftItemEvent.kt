package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.NftItem
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
 * @param itemId 
 * @param type 
 * @param item 
 */
data class NftItemEvent(

    @field:JsonProperty("eventId", required = true) val eventId: kotlin.String,

    @field:JsonProperty("itemId", required = true) val itemId: kotlin.String,

    @field:JsonProperty("type", required = true) val type: NftItemEvent.Type,

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

