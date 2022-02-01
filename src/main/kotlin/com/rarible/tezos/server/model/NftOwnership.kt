package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param owner 
 * @param creators 
 * @param &#x60;value&#x60; 
 * @param lazyValue 
 * @param date 
 * @param createdAt 
 */
data class NftOwnership(

    @field:JsonProperty("id", required = true) val id: kotlin.String,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String,

    @field:JsonProperty("owner", required = true) val owner: kotlin.String,

    @field:Valid
    @field:JsonProperty("creators", required = true) val creators: kotlin.collections.List<Part>,

    @field:JsonProperty("value", required = true) val `value`: kotlin.String,

    @field:JsonProperty("lazyValue", required = true) val lazyValue: kotlin.String,

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:JsonProperty("createdAt", required = true) val createdAt: java.time.OffsetDateTime
) {

}

