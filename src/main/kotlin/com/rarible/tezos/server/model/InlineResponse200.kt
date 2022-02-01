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
 * @param level 
 * @param timestamp 
 * @param chainId 
 * @param chainTimestamp 
 */
data class InlineResponse200(

    @get:Min(-2147483648)
    @get:Max(2147483647)
    @field:JsonProperty("level", required = true) val level: kotlin.Int,

    @field:JsonProperty("timestamp", required = true) val timestamp: java.time.OffsetDateTime,

    @field:JsonProperty("chain_id", required = true) val chainId: kotlin.String,

    @field:JsonProperty("chain_timestamp", required = true) val chainTimestamp: java.time.OffsetDateTime
) {

}

