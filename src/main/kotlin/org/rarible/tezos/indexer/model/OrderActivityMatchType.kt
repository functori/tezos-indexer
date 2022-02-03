package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: sELL,aCCEPTBID
*/
enum class OrderActivityMatchType(val value: kotlin.String) {

    @JsonProperty("SELL") sELL("SELL"),

    @JsonProperty("ACCEPT_BID") aCCEPTBID("ACCEPT_BID");

}

