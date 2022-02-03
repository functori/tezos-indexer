package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param assetClass 
 */
data class XTZAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: XTZAssetType.AssetClass
) {

    /**
    * 
    * Values: xTZ
    */
    enum class AssetClass(val value: kotlin.String) {
    
        @JsonProperty("XTZ") xTZ("XTZ");
    
    }

}

