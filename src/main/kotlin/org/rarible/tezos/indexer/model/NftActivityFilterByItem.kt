package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 * @param contract 
 * @param tokenId 
 */
data class NftActivityFilterByItem(

    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterAllType>,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:JsonProperty("@type", required = true)
    override val type: Type
) : NftActivityFilter(type)

