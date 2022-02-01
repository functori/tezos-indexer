package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param orders 
 * @param continuation 
 */
data class OrderPagination(

    @field:Valid
    @field:JsonProperty("orders", required = true) val orders: kotlin.collections.List<Order>,

    @field:JsonProperty("continuation") val continuation: kotlin.String? = null
) {

}

