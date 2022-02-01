package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.OrderActivityFilterAllType
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
 * @param atType 
 * @param types 
 */
data class OrderActivityFilterAll(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityFilterAll.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<OrderActivityFilterAllType>
) {

    /**
    * 
    * Values: all
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("all") all("all");
    
    }

}

