package com.rarible.protocol.tezos.api.util.tzkt.api.models

import com.squareup.moshi.Json

data class IdParameter (
    @Json(name= "eq" )
    var eq : String? = null,

    @Json(name= "ne" )
    var ne : String? = null,

    @Json(name= "gt" )
    var gt : String? = null,

    @Json(name= "ge" )
    var ge : String? = null,

    @Json(name= "lt" )
    var lt : String? = null,

    @Json(name= "le" )
    var le : String? = null,

    @Json(name= "in" )
    var in_ : String? = null,

    @Json(name = "ni")
    var ni: String? = null
)

