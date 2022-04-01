package com.rarible.protocol.tezos.api.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.protocol.tezos.api.model.NftOwnership

//import javax.validation.constraints.Max
//import javax.validation.constraints.Min
//import javax.validation.Valid

/**
 *
 * @param total
 * @param items
 * @param continuation
 */
data class NftOwnerships(

//    @get:Min(0)
//    @get:Max(9007199254740992)
    @field:JsonProperty("total", required = true) val total: Int,

//    @field:Valid
    @field:JsonProperty("ownerships", required = true) val ownerships: List<NftOwnership>,

    @field:JsonProperty("continuation") val continuation: String? = null
) {

}
