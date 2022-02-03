package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * 
 * @param account 
 * @param &#x60;value&#x60; 
 */
data class Part(

    @field:JsonProperty("account", required = true) val account: kotlin.String,

    @get:Min(0)
    @get:Max(2147483647)
    @field:JsonProperty("value", required = true) val `value`: kotlin.Int
) {

}

