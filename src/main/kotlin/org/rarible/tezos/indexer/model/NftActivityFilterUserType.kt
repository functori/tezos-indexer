package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: tRANSFERFROM,tRANSFERTO,mINT,bURN
*/
enum class NftActivityFilterUserType(val value: kotlin.String) {

    @JsonProperty("TRANSFER_FROM") tRANSFERFROM("TRANSFER_FROM"),

    @JsonProperty("TRANSFER_TO") tRANSFERTO("TRANSFER_TO"),

    @JsonProperty("MINT") mINT("MINT"),

    @JsonProperty("BURN") bURN("BURN");

}

