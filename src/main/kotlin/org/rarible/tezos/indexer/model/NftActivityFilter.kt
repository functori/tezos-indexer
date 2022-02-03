package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
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

