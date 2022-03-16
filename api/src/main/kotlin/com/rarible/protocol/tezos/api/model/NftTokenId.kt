package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param tokenId
 * @param signature
 */
data class NftTokenId(

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:Valid
    @field:JsonProperty("signature") val signature: NftSignature? = null
) {

}

