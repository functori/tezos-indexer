package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param tokenId 
 * @param signature 
 */
data class NftTokenId(

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String,

    @field:Valid
    @field:JsonProperty("signature") val signature: NftSignature? = null
) {

}

