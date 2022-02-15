package org.rarible.tezos.indexer.model.activities

import com.fasterxml.jackson.annotation.JsonProperty
import org.rarible.tezos.indexer.model.OrderActType
import javax.validation.Valid

/**
 * 
 * @param nftType 
 * @param orderType 
 */
data class ActivityType(

    @field:Valid
    @field:JsonProperty("nftType") val nftType: NftActivity? = null,

    @field:Valid
    @field:JsonProperty("orderType") val orderType: OrderActType? = null
) {

}

