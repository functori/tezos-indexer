package org.rarible.tezos.indexer.model

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
    override val type: NftActivityFilter.Type,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: List<NftActivityFilterUserType>,

    @field:JsonProperty("users", required = true) val users: List<String>
) : NftActivityFilter(type)

