package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param id
 * @param date
 * @param source
 * @param type
 */
data class OrderActType(

    @field:JsonProperty("id", required = true) val id: String,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("source", required = true) val source: String,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivityType
) {

}

