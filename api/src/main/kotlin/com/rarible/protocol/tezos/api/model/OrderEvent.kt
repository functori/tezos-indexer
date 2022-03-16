package com.rarible.protocol.tezos.api.model

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

    @field:JsonProperty("eventId", required = true) val eventId: String,

    @field:JsonProperty("orderId", required = true) val orderId: String,

    @field:JsonProperty("type", required = true) val type: OrderEvent.Type,

    @field:Valid
    @field:JsonProperty("order", required = true) val order: Order
) {

    /**
    *
    * Values: uPDATE
    */
    enum class Type(val value: String) {

        @JsonProperty("UPDATE") uPDATE("UPDATE");

    }

}

