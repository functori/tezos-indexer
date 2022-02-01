package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.Part
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
 * @param dataType 
 * @param payouts 
 * @param originFees 
 */
data class OrderRaribleV2DataV1(

    @field:JsonProperty("dataType", required = true) val dataType: kotlin.String,

    @field:Valid
    @field:JsonProperty("payouts", required = true) val payouts: kotlin.collections.List<Part>,

    @field:Valid
    @field:JsonProperty("originFees", required = true) val originFees: kotlin.collections.List<Part>
) {

}

