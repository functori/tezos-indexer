package org.rarible.tezos.indexer.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import org.rarible.tezos.indexer.model.Part
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

