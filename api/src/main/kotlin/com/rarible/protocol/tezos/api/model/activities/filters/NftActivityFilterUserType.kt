package com.rarible.protocol.tezos.api.model.activities.filters

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: tRANSFERFROM,tRANSFERTO,mINT,bURN
*/
enum class NftActivityFilterUserType(val value: String) {

    @JsonProperty("TRANSFER_FROM") TRANSFERFROM("TRANSFER_FROM"),

    @JsonProperty("TRANSFER_TO") TRANSFERTO("TRANSFER_TO"),

    @JsonProperty("MINT") MINT("MINT"),

    @JsonProperty("BURN") BURN("BURN");

}

