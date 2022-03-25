package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.squareup.moshi.Json

data class Attribute(
    @Json(name="name")
    val name: String?,
    @Json(name="trait_type")
    val traitType: String?,

    @Json(name="type")
    val type: String?,
    @Json(name="display_type")
    val displayType: String?,

    @Json(name="value")
    val value: Any?
)
