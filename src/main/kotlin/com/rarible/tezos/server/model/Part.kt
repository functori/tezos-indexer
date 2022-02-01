package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param account 
 * @param &#x60;value&#x60; 
 */
data class Part(

    @field:JsonProperty("account", required = true) val account: kotlin.String,

    @get:Min(0)
    @get:Max(2147483647)
    @field:JsonProperty("value", required = true) val `value`: kotlin.Int
) {

}

