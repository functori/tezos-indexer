package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param hash 
 * @param maker 
 * @param make 
 * @param take 
 * @param transactionHash 
 * @param blockHash 
 * @param blockNumber 
 * @param logIndex 
 */
data class OrderActivityCancelBid(

    @field:JsonProperty("@type", required = true) val atType: OrderActivityCancelBid.AtType,

    @field:JsonProperty("hash", required = true) val hash: kotlin.String,

    @field:JsonProperty("maker", required = true) val maker: kotlin.String,

    @field:Valid
    @field:JsonProperty("make", required = true) val make: AssetType,

    @field:Valid
    @field:JsonProperty("take", required = true) val take: AssetType,

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
    * Values: cancelBid
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("cancel_bid") cancelBid("cancel_bid");
    
    }

}

