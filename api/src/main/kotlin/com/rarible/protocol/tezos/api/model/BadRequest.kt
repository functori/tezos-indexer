package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param code
 * @param message
 */
data class BadRequest(

    @field:JsonProperty("code", required = true) val code: BadRequest.Code,

    @field:JsonProperty("message", required = true) val message: String
) {

    /**
    *
    * Values: bADREQUEST,vALIDATION
    */
    enum class Code(val value: String) {

        @JsonProperty("BAD_REQUEST") bADREQUEST("BAD_REQUEST"),

        @JsonProperty("VALIDATION") vALIDATION("VALIDATION");

    }

}

