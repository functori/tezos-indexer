package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

class AsUnlikeFilterImpl: AsUnlikeFilter {
    /* **Same as** filter mode. \\ Specify a string template to get items where the specified field matches the specified template. \\ This mode supports wildcard `*`. Use `\\*` as an escape symbol.  Example: `?parameter.as=*mid*` or `?parameter.as=*end`. */
    @Json(name = "as")
    override val `as`: Any? = null

    /* **Unlike** filter mode. \\ Specify a string template to get items where the specified field doesn't match the specified template. This mode supports wildcard `*`. Use `\\*` as an escape symbol.  Example: `?parameter.un=*mid*` or `?parameter.un=*end`. */
    @Json(name = "un")
    override val un: Any? = null
}