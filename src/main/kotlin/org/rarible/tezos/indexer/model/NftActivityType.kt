package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param owner 
 * @param contract 
 * @param tokenId 
 * @param value;
 * @param transactionHash 
 * @param blockHash 
 * @param blockNumber 
 * @param elt 
 * @param from 
 */
data class NftActivityType(

    @field:JsonProperty("@type", required = true)
    val type: NftActivityFilter.Type,

    @field:JsonProperty("owner", required = true) val owner: String,

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("tokenId", required = true) val tokenId: String,

    @field:Valid
    @field:JsonProperty("value", required = true) val `value`: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) val transactionHash: String,

    @field:JsonProperty("blockHash", required = true) val blockHash: String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) val blockNumber: Int,

    @field:Valid
    @field:JsonProperty("elt", required = true) val elt: NftActivityElt,

    @field:JsonProperty("from", required = true) val from: String
) {

    /**
    * 
    * Values: transfer
    */
    enum class AtType(val value: String) {
    
        @JsonProperty("transfer") transfer("transfer");
    
    }

}

