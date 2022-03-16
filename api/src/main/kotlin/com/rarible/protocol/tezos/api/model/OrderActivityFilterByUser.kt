package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
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

    @field:JsonProperty("users", required = true) val users: kotlin.collections.List<String>
) {

    /**
    *
    * Values: byUser
    */
    enum class AtType(val value: String) {

        @JsonProperty("by_user") byUser("by_user");

    }

}

