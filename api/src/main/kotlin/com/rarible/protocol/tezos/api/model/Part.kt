package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.moshi.Json
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 *
 * @param account
 * @param &#x60;value&#x60;
 */
data class Part(
    @Json(name = "account")
    @field:JsonProperty("account", required = true) val account: String,

    @Json(name = "value")
    @get:Min(0)
    @get:Max(2147483647)
    @field:JsonProperty("value", required = true) var `value`: Int
) {

}

