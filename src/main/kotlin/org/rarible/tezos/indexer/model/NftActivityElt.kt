package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.Valid

/**
 * 
 * @param owner 
 * @param contract 
 * @param tokenId 
 * @param value
 * @param transactionHash 
 * @param blockHash 
 * @param blockNumber 
 */
data class NftActivityElt(

    @field:JsonProperty("owner", required = true) val owner: String,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:Valid
    @field:JsonProperty("value", required = true) val `value`: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) val transactionHash: String,

    @field:JsonProperty("blockHash", required = true) val blockHash: String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) val blockNumber: Int
)

