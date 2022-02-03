package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param assetClass 
 * @param contract 
 * @param tokenId 
 */
data class NFTAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: NFTAssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String
) {

    /**
    * 
    * Values: nFT
    */
    enum class AssetClass(val value: kotlin.String) {
    
        @JsonProperty("NFT") nFT("NFT");
    
    }

}

