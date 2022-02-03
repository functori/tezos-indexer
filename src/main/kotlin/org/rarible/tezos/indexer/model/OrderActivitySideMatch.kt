package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param maker 
 * @param hash 
 * @param asset 
 * @param type 
 */
data class OrderActivitySideMatch(

    @field:JsonProperty("maker", required = true) val maker: kotlin.String,

    @field:JsonProperty("hash", required = true) val hash: kotlin.String,

    @field:Valid
    @field:JsonProperty("asset", required = true) val asset: Asset,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivitySideType
) {

}

