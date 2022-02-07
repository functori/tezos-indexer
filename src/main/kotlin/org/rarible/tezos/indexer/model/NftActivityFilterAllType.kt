package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: TRANSFER,MINT,BURN
*/
enum class NftActivityFilterAllType(val value: String) {

    @JsonProperty("TRANSFER") TRANSFER("TRANSFER"),

    @JsonProperty("MINT") MINT("MINT"),

    @JsonProperty("BURN") BURN("BURN");

}

