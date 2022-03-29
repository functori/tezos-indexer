package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.moshi.Json
//import javax.validation.Valid

/**
 *
 * @param id
 * @param contract
 * @param tokenId
 * @param owner
 * @param creators
 * @param &#x60;value&#x60;
 * @param lazyValue
 * @param date
 * @param createdAt
 */
data class NftOwnership(

    @field:JsonProperty("id", required = true) val id: String,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:JsonProperty("owner", required = true) val owner: String,

//    @field:Valid
    @field:JsonProperty("creators", required = true) val creators: kotlin.collections.List<Part>,

    @field:JsonProperty("value", required = true) val `value`: String,

    @field:JsonProperty("lazyValue", required = true) val lazyValue: String,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("createdAt", required = true) val createdAt: java.time.OffsetDateTime
) {

}
