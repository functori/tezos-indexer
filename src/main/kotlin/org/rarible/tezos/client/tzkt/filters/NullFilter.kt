package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

interface NullFilter {
    @Json(name = "null")
    val `null`: Boolean?
}