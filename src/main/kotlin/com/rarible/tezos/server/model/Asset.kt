package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.AssetType
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
 * @param assetType 
 * @param &#x60;value&#x60; 
 */
data class Asset(

    @field:Valid
    @field:JsonProperty("assetType", required = true) val assetType: AssetType,

    @field:Valid
    @field:JsonProperty("value", required = true) val `value`: java.math.BigDecimal
) {

}

