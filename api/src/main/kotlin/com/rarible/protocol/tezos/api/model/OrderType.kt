package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: sELL,bID
*/
enum class OrderType(val value: String) {

    @JsonProperty("SELL") sELL("SELL"),

    @JsonProperty("BID") bID("BID");

}

