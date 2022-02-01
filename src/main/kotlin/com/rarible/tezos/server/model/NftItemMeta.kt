package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftItemAttribute
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
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

