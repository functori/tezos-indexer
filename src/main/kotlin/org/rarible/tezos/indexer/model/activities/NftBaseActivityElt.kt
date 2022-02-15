package org.rarible.tezos.indexer.model.activities

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
open class NftBaseActivityElt(

    @field:JsonProperty("owner", required = true) open val owner: String,

    @field:JsonProperty("contract", required = true) open val contract: String,

    @field:JsonProperty("tokenId", required = true) open val tokenId: String,

    @field:Valid
    @field:JsonProperty("value", required = true) open val `value`: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) open val transactionHash: String,

    @field:JsonProperty("blockHash", required = true) open val blockHash: String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) open val blockNumber: Int
)

