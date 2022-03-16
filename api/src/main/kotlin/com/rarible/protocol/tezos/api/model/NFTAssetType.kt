package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param assetClass
 * @param contract
 * @param tokenId
 */
data class NFTAssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: NFTAssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String
) {

    /**
    *
    * Values: nFT
    */
    enum class AssetClass(val value: String) {

        @JsonProperty("NFT") nFT("NFT");

    }

}

