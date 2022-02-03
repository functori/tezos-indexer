package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

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

