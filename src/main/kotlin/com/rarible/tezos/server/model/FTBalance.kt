package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param contract 
 * @param owner 
 * @param balance 
 */
data class FTBalance(

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("owner", required = true) val owner: kotlin.String,

    @field:Valid
    @field:JsonProperty("balance", required = true) val balance: java.math.BigDecimal
) {

}

