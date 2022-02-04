package org.rarible.tezos.client.tzkt.filters

import com.squareup.moshi.Json

class EqualityFilterImpl: EqualityFilter {
    /* **Equal** filter mode (optional, i.e. `param.eq=123` is the same as `param=123`). \\ Specify an account type to get items where the specified field is equal to the specified value.  Example: `?type=delegate`. */
    @Json(name = "eq")
    override val eq: String? = null

    /* **Not equal** filter mode. \\ Specify an account type to get items where the specified field is not equal to the specified value.  Example: `?type.ne=contract`. */
    @Json(name = "ne")
    override  val ne: String? = null

    override fun getEqualityFilterValue(): String {
        return eq.toString() ?: ne.toString() ?: ""
    }

    override fun applyFilterToField(filter: Any): String{
        return if (eq != null){
            "$filter.eq"
        } else if (ne != null){
            "$filter.ne"
        } else {
            "$filter"
        }
    }
}