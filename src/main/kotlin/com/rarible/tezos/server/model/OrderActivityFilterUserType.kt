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
* Values: cANCELLIST,cANCELBID,mAKEBID,gETBID,lIST,bUY,sELL
*/
enum class OrderActivityFilterUserType(val value: kotlin.String) {

    @JsonProperty("CANCEL_LIST") cANCELLIST("CANCEL_LIST"),

    @JsonProperty("CANCEL_BID") cANCELBID("CANCEL_BID"),

    @JsonProperty("MAKE_BID") mAKEBID("MAKE_BID"),

    @JsonProperty("GET_BID") gETBID("GET_BID"),

    @JsonProperty("LIST") lIST("LIST"),

    @JsonProperty("BUY") bUY("BUY"),

    @JsonProperty("SELL") sELL("SELL");

}

