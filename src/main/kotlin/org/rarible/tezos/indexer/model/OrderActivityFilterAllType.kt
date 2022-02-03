package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: bID,lIST,mATCH,cANCELLIST,cANCELBID
*/
enum class OrderActivityFilterAllType(val value: kotlin.String) {

    @JsonProperty("BID") bID("BID"),

    @JsonProperty("LIST") lIST("LIST"),

    @JsonProperty("MATCH") mATCH("MATCH"),

    @JsonProperty("CANCEL_LIST") cANCELLIST("CANCEL_LIST"),

    @JsonProperty("CANCEL_BID") cANCELBID("CANCEL_BID");

}

