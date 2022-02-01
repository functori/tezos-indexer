package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftCollection
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
 * @param total 
 * @param collections 
 * @param continuation 
 */
data class NftCollections(

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("total", required = true) val total: kotlin.Int,

    @field:Valid
    @field:JsonProperty("collections", required = true) val collections: kotlin.collections.List<NftCollection>,

    @field:JsonProperty("continuation") val continuation: kotlin.String? = null
) {

}

