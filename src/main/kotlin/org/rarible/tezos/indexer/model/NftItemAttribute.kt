package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param key 
 * @param &#x60;value&#x60; 
 * @param type 
 * @param format 
 */
data class NftItemAttribute(

    @field:JsonProperty("key", required = true) val key: kotlin.String,

    @field:JsonProperty("value", required = true) val `value`: kotlin.String,

    @field:JsonProperty("type") val type: kotlin.String? = null,

    @field:JsonProperty("format") val format: kotlin.String? = null
) {

}

