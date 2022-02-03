package org.rarible.tezos.indexer.model

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

    @field:JsonProperty("address", required = true) val address: kotlin.String,

    @field:JsonProperty("edpk", required = true) val edpk: kotlin.String,

    @field:JsonProperty("message", required = true) val message: kotlin.String,

    @field:JsonProperty("signature", required = true) val signature: kotlin.String,

    @field:JsonProperty("prefix") val prefix: kotlin.String? = null
) {

}

