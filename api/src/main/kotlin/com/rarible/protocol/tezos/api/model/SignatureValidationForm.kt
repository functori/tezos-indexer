package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param address
 * @param edpk
 * @param message
 * @param signature
 * @param prefix
 */
data class SignatureValidationForm(

    @field:JsonProperty("address", required = true) val address: String,

    @field:JsonProperty("edpk", required = true) val edpk: String,

    @field:JsonProperty("message", required = true) val message: String,

    @field:JsonProperty("signature", required = true) val signature: String,

    @field:JsonProperty("prefix") val prefix: String? = null
) {

}

