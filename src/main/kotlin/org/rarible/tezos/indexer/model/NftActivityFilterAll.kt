package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 */
data class NftActivityFilterAll(

    @field:JsonProperty("@type", required = true) val atType: NftActivityFilterAll.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<NftActivityFilterAllType>
) {

    /**
    * 
    * Values: all
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("all") all("all");
    
    }

}

