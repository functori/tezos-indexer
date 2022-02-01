package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftItemMeta
import com.rarible.tezos.server.model.Part
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
 * @param id 
 * @param contract 
 * @param tokenId 
 * @param creators 
 * @param supply 
 * @param lazySupply 
 * @param owners 
 * @param royalties 
 * @param date 
 * @param mintedAt 
 * @param deleted 
 * @param onchainRoyalties 
 * @param meta 
 */
data class NftItem(

    @field:JsonProperty("id", required = true) val id: kotlin.String,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String,

    @field:Valid
    @field:JsonProperty("creators", required = true) val creators: kotlin.collections.List<Part>,

    @field:JsonProperty("supply", required = true) val supply: kotlin.String,

    @field:JsonProperty("lazySupply", required = true) val lazySupply: kotlin.String,

    @field:JsonProperty("owners", required = true) val owners: kotlin.collections.List<kotlin.String>,

    @field:Valid
    @field:JsonProperty("royalties", required = true) val royalties: kotlin.collections.List<Part>,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("mintedAt", required = true) val mintedAt: java.time.OffsetDateTime,

    @field:JsonProperty("deleted", required = true) val deleted: kotlin.Boolean,

    @field:JsonProperty("onchainRoyalties") val onchainRoyalties: kotlin.Boolean? = null,

    @field:Valid
    @field:JsonProperty("meta") val meta: NftItemMeta? = null
) {

}

