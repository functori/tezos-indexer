package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param code
 * @param message
 */
data class ServerError(

    @field:JsonProperty("code", required = true) val code: ServerError.Code,

    @field:JsonProperty("message", required = true) val message: String
) {

    /**
    *
    * Values: uNEXPECTEDAPIERROR
    */
    enum class Code(val value: String) {

        @JsonProperty("UNEXPECTED_API_ERROR") uNEXPECTEDAPIERROR("UNEXPECTED_API_ERROR");

    }

}

