package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param royalties 
 * @param onchain 
 */
data class NftItemRoyalties(

    @field:Valid
    @field:JsonProperty("royalties", required = true) val royalties: kotlin.collections.List<Part>,

    @field:JsonProperty("onchain") val onchain: kotlin.Boolean? = null
) {

}

