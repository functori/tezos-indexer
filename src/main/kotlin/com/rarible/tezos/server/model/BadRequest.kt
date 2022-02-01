package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
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
 * @param code 
 * @param message 
 */
data class BadRequest(

    @field:JsonProperty("code", required = true) val code: BadRequest.Code,

    @field:JsonProperty("message", required = true) val message: kotlin.String
) {

    /**
    * 
    * Values: bADREQUEST,vALIDATION
    */
    enum class Code(val value: kotlin.String) {
    
        @JsonProperty("BAD_REQUEST") bADREQUEST("BAD_REQUEST"),
    
        @JsonProperty("VALIDATION") vALIDATION("VALIDATION");
    
    }

}

