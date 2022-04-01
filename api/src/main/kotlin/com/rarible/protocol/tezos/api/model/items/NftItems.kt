package com.rarible.protocol.tezos.api.model.items

import com.fasterxml.jackson.annotation.JsonProperty
//import javax.validation.constraints.Max
//import javax.validation.constraints.Min
//import javax.validation.Valid

/**
 *
 * @param total
 * @param items
 * @param continuation
 */
data class NftItems(

//    @get:Min(0)
//    @get:Max(9007199254740992)
    @field:JsonProperty("total", required = true) val total: Int,

//    @field:Valid
    @field:JsonProperty("items", required = true) val items: List<NftItem>,

    @field:JsonProperty("continuation") val continuation: String? = null
) {

}