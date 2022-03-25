package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.squareup.moshi.Json

data class Dimensions(
    @Json(name="value")
    val value: String,

    @Json(name="unit")
    val unit: String
)
