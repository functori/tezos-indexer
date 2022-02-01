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
 * @param address 
 * @param edpk 
 * @param message 
 * @param signature 
 * @param prefix 
 */
data class SignatureValidationForm(

    @field:JsonProperty("address", required = true) val address: kotlin.String,

    @field:JsonProperty("edpk", required = true) val edpk: kotlin.String,

    @field:JsonProperty("message", required = true) val message: kotlin.String,

    @field:JsonProperty("signature", required = true) val signature: kotlin.String,

    @field:JsonProperty("prefix") val prefix: kotlin.String? = null
) {

}

