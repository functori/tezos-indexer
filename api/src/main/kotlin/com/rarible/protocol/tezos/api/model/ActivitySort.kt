package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: lATESTFIRST,eARLIESTFIRST
*/
enum class ActivitySort(val value: String) {

    @JsonProperty("LATEST_FIRST") LATESTFIRST("LATEST_FIRST"),

    @JsonProperty("EARLIEST_FIRST") EARLIESTFIRST("EARLIEST_FIRST");

}

