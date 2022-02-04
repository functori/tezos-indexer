package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

class AnyAllFilterImpl: AnyAllFilter {
    /* **In list** (any of) filter mode. \\ Specify a comma-separated list of addresses to get items where the specified field is equal to one of the specified values.  Example: `?sender.in=tz1WnfXMPaNTB,tz1SiPXX4MYGNJND`. */
    @Json(name = "any")
    override val any: List<String>? = null

    /* **Not in list** (none of) filter mode. \\ Specify a comma-separated list of addresses to get items where the specified field is not equal to all the specified values.  Example: `?sender.ni=tz1WnfXMPaNTB,tz1SiPXX4MYGNJND`. */
    @Json(name = "all")
    override val all: List<String>? = null
}