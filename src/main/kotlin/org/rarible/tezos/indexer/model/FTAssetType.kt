package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param assetClass 
 * @param contract 
 * @param tokenId 
 */
data class FTAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: FTAssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId") val tokenId: kotlin.String? = null
) {

    /**
    * 
    * Values: fT
    */
    enum class AssetClass(val value: kotlin.String) {
    
        @JsonProperty("FT") fT("FT");
    
    }

}

