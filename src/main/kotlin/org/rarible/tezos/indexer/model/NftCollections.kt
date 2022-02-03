package org.rarible.tezos.indexer.model

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
    @field:JsonProperty("total", required = true) val total: kotlin.Int,

    @field:Valid
    @field:JsonProperty("collections", required = true) val collections: kotlin.collections.List<NftCollection>,

    @field:JsonProperty("continuation") val continuation: kotlin.String? = null
) {

}

