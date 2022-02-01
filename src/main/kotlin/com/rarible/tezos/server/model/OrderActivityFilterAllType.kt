package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
* Values: bID,lIST,mATCH,cANCELLIST,cANCELBID
*/
enum class OrderActivityFilterAllType(val value: kotlin.String) {

    @JsonProperty("BID") bID("BID"),

    @JsonProperty("LIST") lIST("LIST"),

    @JsonProperty("MATCH") mATCH("MATCH"),

    @JsonProperty("CANCEL_LIST") cANCELLIST("CANCEL_LIST"),

    @JsonProperty("CANCEL_BID") cANCELBID("CANCEL_BID");

}

