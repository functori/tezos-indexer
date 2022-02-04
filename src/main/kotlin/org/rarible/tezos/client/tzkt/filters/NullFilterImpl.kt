package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

class NullFilterImpl: NullFilter {
    @Json(name = "null")
    override val `null`: Boolean? = null
}