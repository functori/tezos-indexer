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

