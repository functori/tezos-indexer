package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param maker
 * @param hash
 * @param asset
 * @param type
 */
data class OrderActivitySideMatch(

    @field:JsonProperty("maker", required = true) val maker: String,

    @field:JsonProperty("hash", required = true) val hash: String,

    @field:Valid
    @field:JsonProperty("asset", required = true) val asset: com.rarible.protocol.tezos.api.model.Asset,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivitySideType
) {

}

