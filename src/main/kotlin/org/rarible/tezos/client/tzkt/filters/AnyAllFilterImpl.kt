package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

class AnyAllFilterImpl: AnyAllFilter {
    /* **In list** (any of) filter mode. \\ Specify a comma-separated list of addresses to get items where the specified field is equal to one of the specified values.  Example: `?sender.in=tz1WnfXMPaNTB,tz1SiPXX4MYGNJND`. */
    @Json(name = "any")
    override val any: List<String>? = null

    /* **Not in list** (none of) filter mode. \\ Specify a comma-separated list of addresses to get items where the specified field is not equal to all the specified values.  Example: `?sender.ni=tz1WnfXMPaNTB,tz1SiPXX4MYGNJND`. */
    @Json(name = "all")
    override val all: List<String>? = null

    override fun getFilter(): String{
        return if(!any.isNullOrEmpty()){
            "any"
        } else if (!all.isNullOrEmpty()) {
            "all"
        } else {
            ""
        }
    }

    override fun getFilterValue(): String{
        var value = ""
        if(!any.isNullOrEmpty()){
            any!!.forEach {  value = "$value,$it"}
        } else if (!all.isNullOrEmpty()) {
            all!!.forEach {  value = "$value,$it"}
        } else {
            ""
        }
        return value
    }
}