package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param code
 * @param message
 */
data class OrderError(

    @field:JsonProperty("code", required = true) val code: OrderError.Code,

    @field:JsonProperty("message", required = true) val message: String
) {

    /**
    *
    * Values: oRDERINVALIDUPDATE,oRDERCANCELED,iNCORRECTSIGNATURE
    */
    enum class Code(val value: String) {

        @JsonProperty("ORDER_INVALID_UPDATE") oRDERINVALIDUPDATE("ORDER_INVALID_UPDATE"),

        @JsonProperty("ORDER_CANCELED") oRDERCANCELED("ORDER_CANCELED"),

        @JsonProperty("INCORRECT_SIGNATURE") iNCORRECTSIGNATURE("INCORRECT_SIGNATURE");

    }

}

