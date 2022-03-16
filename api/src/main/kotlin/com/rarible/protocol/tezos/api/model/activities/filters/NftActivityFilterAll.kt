package com.rarible.protocol.tezos.api.model.activities.filters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param atType
 * @param types
 */
data class NftActivityFilterAll(
    @field:JsonProperty("@type", required = true)
    override val type: Type = Type.all,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterAllType>,
) : NftActivityFilter(type)

