package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
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

