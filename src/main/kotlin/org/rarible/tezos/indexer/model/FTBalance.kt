package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.Valid

/**
 * 
 * @param contract 
 * @param owner 
 * @param balance 
 */
data class FTBalance(

    @field:JsonProperty("contract", required = true) val contract: String,

    @field:JsonProperty("owner", required = true) val owner: String,

    @field:Valid
    @field:JsonProperty("balance", required = true) val balance: BigDecimal
) {

}

