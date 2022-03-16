package com.rarible.protocol.tezos.api.model.activities.filters

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param atType
 * @param types
 * @param users
 */
data class NftActivityFilterByUser(

    @field:JsonProperty("@type", required = true)
    override val type: Type = Type.byUser,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterUserType>,

    @field:JsonProperty("users", required = true) val users: List<String>
) : NftActivityFilter(type)

