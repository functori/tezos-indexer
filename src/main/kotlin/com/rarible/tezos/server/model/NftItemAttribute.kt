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
 * @param key 
 * @param &#x60;value&#x60; 
 * @param type 
 * @param format 
 */
data class NftItemAttribute(

    @field:JsonProperty("key", required = true) val key: kotlin.String,

    @field:JsonProperty("value", required = true) val `value`: kotlin.String,

    @field:JsonProperty("type") val type: kotlin.String? = null,

    @field:JsonProperty("format") val format: kotlin.String? = null
) {

}

