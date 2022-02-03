package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param items 
 * @param continuation 
 */
data class NftActivities(

    @field:Valid
    @field:JsonProperty("items", required = true) val items: kotlin.collections.List<NftActType>,

    @field:JsonProperty("continuation") val continuation: kotlin.String? = null
) {

}

