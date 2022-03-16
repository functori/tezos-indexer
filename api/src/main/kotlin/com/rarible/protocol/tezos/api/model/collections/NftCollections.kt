package com.rarible.protocol.tezos.api.model.collections

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
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
    @field:JsonProperty("total", required = true) val total: Int,

    @field:Valid
    @field:JsonProperty("collections", required = true) val collections: List<NftCollection>,

    @field:JsonProperty("continuation") val continuation: String? = null
) {

}

