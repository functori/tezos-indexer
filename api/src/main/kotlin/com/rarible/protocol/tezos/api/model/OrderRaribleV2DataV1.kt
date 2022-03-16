package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param dataType
 * @param payouts
 * @param originFees
 */
data class OrderRaribleV2DataV1(

    @field:JsonProperty("dataType", required = true) val dataType: String,

    @field:Valid
    @field:JsonProperty("payouts", required = true) val payouts: kotlin.collections.List<Part>,

    @field:Valid
    @field:JsonProperty("originFees", required = true) val originFees: kotlin.collections.List<Part>
) {

}

