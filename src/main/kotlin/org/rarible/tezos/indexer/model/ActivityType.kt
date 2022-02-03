package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param nftType 
 * @param orderType 
 */
data class ActivityType(

    @field:Valid
    @field:JsonProperty("nftType") val nftType: NftActType? = null,

    @field:Valid
    @field:JsonProperty("orderType") val orderType: OrderActType? = null
) {

}

