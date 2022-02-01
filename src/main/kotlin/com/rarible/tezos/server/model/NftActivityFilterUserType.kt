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
* Values: tRANSFERFROM,tRANSFERTO,mINT,bURN
*/
enum class NftActivityFilterUserType(val value: kotlin.String) {

    @JsonProperty("TRANSFER_FROM") tRANSFERFROM("TRANSFER_FROM"),

    @JsonProperty("TRANSFER_TO") tRANSFERTO("TRANSFER_TO"),

    @JsonProperty("MINT") mINT("MINT"),

    @JsonProperty("BURN") bURN("BURN");

}

