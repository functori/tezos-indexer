package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param left 
 * @param right 
 * @param type 
 * @param price 
 * @param transactionHash 
 * @param blockHash 
 * @param blockNumber 
 * @param logIndex 
 */
data class OrderActivityMatch(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityMatch.AtType,

    @field:Valid
    @field:JsonProperty("left", required = true) val left: OrderActivitySideMatch,

    @field:Valid
    @field:JsonProperty("right", required = true) val right: OrderActivitySideMatch,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: OrderActivityMatchType,

    @field:Valid
    @field:JsonProperty("price", required = true) val price: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) val transactionHash: kotlin.String,

    @field:JsonProperty("blockHash", required = true) val blockHash: kotlin.String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) val blockNumber: kotlin.Int,

    @get:Min(-1073741824)
    @get:Max(1073741823)
    @field:JsonProperty("logIndex", required = true) val logIndex: kotlin.Int
) {

    /**
    * 
    * Values: match
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("match") match("match");
    
    }

}

