package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.rarible.tezos.server.model.NftActivityElt
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

