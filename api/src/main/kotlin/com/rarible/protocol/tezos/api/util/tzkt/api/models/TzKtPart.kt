package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.moshi.Json

data class TzKtPart(
    @Json(name="partAccount")
    var partAccount: String,
    /* Value in JSON or Micheline format, depending on the `micheline` query parameter. Note, if the key is inactive (removed) it will contain the last non-null value. */
    @Json(name = "partValue")
    var partValue: String
)

