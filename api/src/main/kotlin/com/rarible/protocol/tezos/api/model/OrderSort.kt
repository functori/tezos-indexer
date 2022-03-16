package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: lATESTFIRST,eARLIESTFIRST
*/
enum class OrderSort(val value: String) {

    @JsonProperty("LATEST_FIRST") lATESTFIRST("LATEST_FIRST"),

    @JsonProperty("EARLIEST_FIRST") eARLIESTFIRST("EARLIEST_FIRST");

}

