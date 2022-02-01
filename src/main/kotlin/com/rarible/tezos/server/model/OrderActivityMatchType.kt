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
* Values: sELL,aCCEPTBID
*/
enum class OrderActivityMatchType(val value: kotlin.String) {

    @JsonProperty("SELL") sELL("SELL"),

    @JsonProperty("ACCEPT_BID") aCCEPTBID("ACCEPT_BID");

}

