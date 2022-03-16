package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param assetClass
 * @param contract
 * @param tokenId
 */
data class AssetType(

    @field:JsonProperty("assetClass", required = true) val assetClass: com.rarible.protocol.tezos.api.model.AssetType.AssetClass,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String
) {

    /**
    *
    * Values: mT
    */
    enum class AssetClass(val value: String) {

        @JsonProperty("MT") mT("MT");

    }

}

