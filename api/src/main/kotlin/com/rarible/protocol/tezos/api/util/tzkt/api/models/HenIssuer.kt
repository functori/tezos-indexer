package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.squareup.moshi.Json

data class HenIssuer (
    @Json(name = "issuer")
    var issuer: String,
    @Json(name = "royalties")
    var royalties: String
)