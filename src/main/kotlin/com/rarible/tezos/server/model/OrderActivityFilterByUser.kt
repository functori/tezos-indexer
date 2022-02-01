package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.OrderActivityFilterUserType
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
 * @param users 
 */
data class OrderActivityFilterByUser(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityFilterByUser.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<OrderActivityFilterUserType>,

    @field:JsonProperty("users", required = true) val users: kotlin.collections.List<kotlin.String>
) {

    /**
    * 
    * Values: byUser
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("by_user") byUser("by_user");
    
    }

}

