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
* Values: aCTIVE,fILLED,hISTORICAL,iNACTIVE,cANCELLED
*/
enum class OrderStatus(val value: kotlin.String) {

    @JsonProperty("ACTIVE") aCTIVE("ACTIVE"),

    @JsonProperty("FILLED") fILLED("FILLED"),

    @JsonProperty("HISTORICAL") hISTORICAL("HISTORICAL"),

    @JsonProperty("INACTIVE") iNACTIVE("INACTIVE"),

    @JsonProperty("CANCELLED") cANCELLED("CANCELLED");

}

