package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param assetClass
 * @param contract
 * @param tokenId
 */
data class FTAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: FTAssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId") val tokenId: String? = null
) {

    /**
    *
    * Values: fT
    */
    enum class AssetClass(val value: String) {

        @JsonProperty("FT") fT("FT");

    }

}

