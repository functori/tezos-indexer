package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.OrderActType
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
 * @param items 
 * @param continuation 
 */
data class OrderActivities(

    @field:Valid
    @field:JsonProperty("items", required = true) val items: kotlin.collections.List<OrderActType>,

    @field:JsonProperty("continuation") val continuation: kotlin.String? = null
) {

}

