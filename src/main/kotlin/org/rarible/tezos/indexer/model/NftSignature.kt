package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

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

