package org.rarible.tezos.indexer.model.activities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param items 
 * @param continuation 
 */
data class NftActivities(

    @field:Valid
    @field:JsonProperty("items", required = true) var items: List<NftActivityElt>,

    @field:JsonProperty("continuation") var continuation: String? = null
) {

}

