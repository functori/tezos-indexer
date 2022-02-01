package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftSignature
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
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

