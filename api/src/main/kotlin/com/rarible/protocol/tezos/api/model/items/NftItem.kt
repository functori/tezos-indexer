package com.rarible.protocol.tezos.api.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.protocol.tezos.api.model.Part
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
    @field:JsonProperty("id", required = true) val id: String,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:Valid
    @field:JsonProperty("creators", required = true) val creators: List<Part>,

    @field:JsonProperty("supply", required = true) val supply: String,

    @field:JsonProperty("lazySupply", required = true) val lazySupply: String,

    @field:JsonProperty("owners", required = true) val owners: List<String>,

    @field:Valid
    @field:JsonProperty("royalties", required = true) val royalties: List<Part>,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("mintedAt", required = true) val mintedAt: java.time.OffsetDateTime,

    @field:JsonProperty("deleted", required = true) val deleted: Boolean,

    @field:JsonProperty("onchainRoyalties") val onchainRoyalties: Boolean? = null,

    @field:Valid
    @field:JsonProperty("meta") val meta: NftItemMeta? = null
) {

}

