package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: lATESTFIRST,eARLIESTFIRST
*/
enum class ActivitySort(val value: kotlin.String) {

    @JsonProperty("LATEST_FIRST") lATESTFIRST("LATEST_FIRST"),

    @JsonProperty("EARLIEST_FIRST") eARLIESTFIRST("EARLIEST_FIRST");

}

