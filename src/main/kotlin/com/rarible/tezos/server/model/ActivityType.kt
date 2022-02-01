package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftActType
import com.rarible.tezos.server.model.OrderActType
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
 * @param nftType 
 * @param orderType 
 */
data class ActivityType(

    @field:Valid
    @field:JsonProperty("nftType") val nftType: NftActType? = null,

    @field:Valid
    @field:JsonProperty("orderType") val orderType: OrderActType? = null
) {

}

