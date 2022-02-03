package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
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

