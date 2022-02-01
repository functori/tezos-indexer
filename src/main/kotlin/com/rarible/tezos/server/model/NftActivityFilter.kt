package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.NftActivityFilterAll
import com.rarible.tezos.server.model.NftActivityFilterAllType
import com.rarible.tezos.server.model.NftActivityFilterByCollection
import com.rarible.tezos.server.model.NftActivityFilterByItem
import com.rarible.tezos.server.model.NftActivityFilterByUser
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
 * @param atType 
 * @param types 
 * @param users 
 * @param contract 
 * @param tokenId 
 */
data class NftActivityFilter(

    @field:JsonProperty("@type", required = true) val atType: NftActivityFilter.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<NftActivityFilterAllType>,

    @field:JsonProperty("users", required = true) val users: kotlin.collections.List<kotlin.String>,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String
) {

    /**
    * 
    * Values: byCollection
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("by_collection") byCollection("by_collection");
    
    }

}

