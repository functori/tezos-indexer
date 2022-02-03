package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param assetClass 
 * @param contract 
 * @param tokenId 
 */
data class MTAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: MTAssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String
) {

    /**
    * 
    * Values: mT
    */
    enum class AssetClass(val value: kotlin.String) {
    
        @JsonProperty("MT") mT("MT");
    
    }

}

