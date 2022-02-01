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
 * @param r 
 * @param s 
 * @param v 
 */
data class NftSignature(

    @field:JsonProperty("r", required = true) val r: kotlin.String,

    @field:JsonProperty("s", required = true) val s: kotlin.String,

    @field:JsonProperty("v", required = true) val v: kotlin.String
) {

}

