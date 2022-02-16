package org.rarible.tezos.indexer.model.activities.filters

import com.fasterxml.jackson.annotation.JsonProperty
import org.rarible.tezos.client.tzkt.models.NFTActivities.type
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 * @param contract 
 */
data class NftActivityFilterByCollection(
    @field:JsonProperty("@type", required = true)
    override val type: Type = Type.byCollection,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterAllType>,

    @field:JsonProperty("contract", required = true) val contract: String,
): NftActivityFilter(type)

