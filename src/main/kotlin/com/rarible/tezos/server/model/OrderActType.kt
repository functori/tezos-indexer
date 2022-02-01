package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.OrderActivityType
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
 * @param id 
 * @param date 
 * @param source 
 * @param type 
 */
data class OrderActType(

    @field:JsonProperty("id", required = true) val id: kotlin.String,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("source", required = true) val source: kotlin.String,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivityType
) {

}

