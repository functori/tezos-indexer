package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

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

