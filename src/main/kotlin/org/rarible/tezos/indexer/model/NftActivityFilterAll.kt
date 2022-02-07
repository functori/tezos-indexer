package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 */
data class NftActivityFilterAll(
    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterAllType>,
    @field:JsonProperty("@type", required = true)
    override val type: Type
) : NftActivityFilter(type)

