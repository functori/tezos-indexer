package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param elt 
 * @param from 
 */
data class Transfer(

    @field:JsonProperty("@type", required = true) val atType: Transfer.AtType,

    @field:Valid
    @field:JsonProperty("elt", required = true) val elt: NftActivityElt,

    @field:JsonProperty("from", required = true) val from: kotlin.String
) {

    /**
    * 
    * Values: transfer
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("transfer") transfer("transfer");
    
    }

}

