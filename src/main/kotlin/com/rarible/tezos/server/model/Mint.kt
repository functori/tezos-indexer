package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param owner 
 * @param contract 
 * @param tokenId 
 * @param &#x60;value&#x60; 
 * @param transactionHash 
 * @param blockHash 
 * @param blockNumber 
 */
data class Mint(

    @field:JsonProperty("@type", required = true) val atType: Mint.AtType,

    @field:JsonProperty("owner", required = true) val owner: kotlin.String,

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("tokenId", required = true) val tokenId: kotlin.String,

    @field:Valid
    @field:JsonProperty("value", required = true) val `value`: java.math.BigDecimal,

    @field:JsonProperty("transactionHash", required = true) val transactionHash: kotlin.String,

    @field:JsonProperty("blockHash", required = true) val blockHash: kotlin.String,

    @get:Min(0)
    @get:Max(9007199254740992)
    @field:JsonProperty("blockNumber", required = true) val blockNumber: kotlin.Int
) {

    /**
    * 
    * Values: mint
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("mint") mint("mint");
    
    }

}

