package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param ids
 */
data class OrderIds(

    @field:JsonProperty("ids", required = true) val ids: kotlin.collections.List<String>
) {

}

