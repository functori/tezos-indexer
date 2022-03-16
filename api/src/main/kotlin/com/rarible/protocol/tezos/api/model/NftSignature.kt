package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param r
 * @param s
 * @param v
 */
data class NftSignature(

    @field:JsonProperty("r", required = true) val r: String,

    @field:JsonProperty("s", required = true) val s: String,

    @field:JsonProperty("v", required = true) val v: String
) {

}

