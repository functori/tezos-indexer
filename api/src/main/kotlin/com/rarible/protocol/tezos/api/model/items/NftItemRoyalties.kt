package com.rarible.protocol.tezos.api.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.protocol.tezos.api.model.Part
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

