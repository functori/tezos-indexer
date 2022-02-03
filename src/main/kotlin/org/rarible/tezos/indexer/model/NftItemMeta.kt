package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param name 
 * @param description 
 * @param attributes 
 * @param image 
 * @param animation 
 */
data class NftItemMeta(

    @field:JsonProperty("name", required = true) val name: kotlin.String,

    @field:JsonProperty("description") val description: kotlin.String? = null,

    @field:Valid
    @field:JsonProperty("attributes") val attributes: kotlin.collections.List<NftItemAttribute>? = null,

    @field:JsonProperty("image") val image: kotlin.String? = null,

    @field:JsonProperty("animation") val animation: kotlin.String? = null
) {

}

