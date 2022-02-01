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
 * @param code 
 * @param message 
 */
data class EntityNotFound(

    @field:JsonProperty("code", required = true) val code: EntityNotFound.Code,

    @field:JsonProperty("message", required = true) val message: kotlin.String
) {

    /**
    * 
    * Values: tOKENNOTFOUND,bALANCENOTFOUND,oWNERSHIPNOTFOUND,oRDERNOTFOUND,iTEMNOTFOUND,cOLLECTIONNOTFOUND,nOTFOUND
    */
    enum class Code(val value: kotlin.String) {
    
        @JsonProperty("TOKEN_NOT_FOUND") tOKENNOTFOUND("TOKEN_NOT_FOUND"),
    
        @JsonProperty("BALANCE_NOT_FOUND") bALANCENOTFOUND("BALANCE_NOT_FOUND"),
    
        @JsonProperty("OWNERSHIP_NOT_FOUND") oWNERSHIPNOTFOUND("OWNERSHIP_NOT_FOUND"),
    
        @JsonProperty("ORDER_NOT_FOUND") oRDERNOTFOUND("ORDER_NOT_FOUND"),
    
        @JsonProperty("ITEM_NOT_FOUND") iTEMNOTFOUND("ITEM_NOT_FOUND"),
    
        @JsonProperty("COLLECTION_NOT_FOUND") cOLLECTIONNOTFOUND("COLLECTION_NOT_FOUND"),
    
        @JsonProperty("NOT_FOUND") nOTFOUND("NOT_FOUND");
    
    }

}

