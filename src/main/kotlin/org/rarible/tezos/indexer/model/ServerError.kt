package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param code 
 * @param message 
 */
data class ServerError(

    @field:JsonProperty("code", required = true) val code: ServerError.Code,

    @field:JsonProperty("message", required = true) val message: kotlin.String
) {

    /**
    * 
    * Values: uNEXPECTEDAPIERROR
    */
    enum class Code(val value: kotlin.String) {
    
        @JsonProperty("UNEXPECTED_API_ERROR") uNEXPECTEDAPIERROR("UNEXPECTED_API_ERROR");
    
    }

}

