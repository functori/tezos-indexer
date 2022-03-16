package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min

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
    @field:JsonProperty("level", required = true) val level: Int,

    @field:JsonProperty("timestamp", required = true) val timestamp: java.time.OffsetDateTime,

    @field:JsonProperty("chain_id", required = true) val chainId: String,

    @field:JsonProperty("chain_timestamp", required = true) val chainTimestamp: java.time.OffsetDateTime
) {

}

