package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: tRANSFER,mINT,bURN
*/
enum class NftActivityFilterAllType(val value: kotlin.String) {

    @JsonProperty("TRANSFER") tRANSFER("TRANSFER"),

    @JsonProperty("MINT") mINT("MINT"),

    @JsonProperty("BURN") bURN("BURN");

}

