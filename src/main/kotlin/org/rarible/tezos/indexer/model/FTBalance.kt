package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param contract 
 * @param owner 
 * @param balance 
 */
data class FTBalance(

    @field:JsonProperty("contract", required = true) val contract: kotlin.String,

    @field:JsonProperty("owner", required = true) val owner: kotlin.String,

    @field:Valid
    @field:JsonProperty("balance", required = true) val balance: java.math.BigDecimal
) {

}

