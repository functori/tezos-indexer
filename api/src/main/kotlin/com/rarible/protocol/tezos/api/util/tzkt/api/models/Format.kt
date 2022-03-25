package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.squareup.moshi.Json

data class Format(
    @Json(name="uri")
    val uri : String,

    @Json(name="hash")
    val hash : String?,

    @Json(name="mimeType")
    val mimeType : String?,

    @Json(name="fileSize")
    val fileSize : Int?,

    @Json(name="fileName")
    val fileName : String?,

    @Json(name="duration")
    val duration : String?,

    @Json(name="dimensions")
    val dimensions : Dimensions?,

    @Json(name="dataRate")
    val dataRate : Dimensions?
)
