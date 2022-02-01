package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
* 
* Values: lATESTFIRST,eARLIESTFIRST
*/
enum class ActivitySort(val value: kotlin.String) {

    @JsonProperty("LATEST_FIRST") lATESTFIRST("LATEST_FIRST"),

    @JsonProperty("EARLIEST_FIRST") eARLIESTFIRST("EARLIEST_FIRST");

}

