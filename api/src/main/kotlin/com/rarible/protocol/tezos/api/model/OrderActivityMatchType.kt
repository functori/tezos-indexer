package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: sELL,aCCEPTBID
*/
enum class OrderActivityMatchType(val value: String) {

    @JsonProperty("SELL") sELL("SELL"),

    @JsonProperty("ACCEPT_BID") aCCEPTBID("ACCEPT_BID");

}

