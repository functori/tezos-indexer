package com.rarible.protocol.tezos.api.model.items

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param key
 * @param &#x60;value&#x60;
 * @param type
 * @param format
 */
data class NftItemAttribute(

    @field:JsonProperty("key", required = true) var key: String? = null,

    @field:JsonProperty("value", required = true) var `value`: String? = null,

    @field:JsonProperty("type") var type: String? = null,

    @field:JsonProperty("format") var format: String? = null
) {

}

