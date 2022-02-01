package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.Order
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
 * @param orderId 
 * @param type 
 * @param order 
 */
data class OrderEvent(

    @field:JsonProperty("eventId", required = true) val eventId: kotlin.String,

    @field:JsonProperty("orderId", required = true) val orderId: kotlin.String,

    @field:JsonProperty("type", required = true) val type: OrderEvent.Type,

    @field:Valid
    @field:JsonProperty("order", required = true) val order: Order
) {

    /**
    * 
    * Values: uPDATE
    */
    enum class Type(val value: kotlin.String) {
    
        @JsonProperty("UPDATE") uPDATE("UPDATE");
    
    }

}

