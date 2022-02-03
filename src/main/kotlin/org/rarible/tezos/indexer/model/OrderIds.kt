package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param ids 
 */
data class OrderIds(

    @field:JsonProperty("ids", required = true) val ids: kotlin.collections.List<kotlin.String>
) {

}

