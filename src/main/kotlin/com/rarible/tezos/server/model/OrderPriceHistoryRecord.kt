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
 * @param date 
 * @param makeValue 
 * @param takeValue 
 */
data class OrderPriceHistoryRecord(

    @field:JsonProperty("date", required = true) val date: java.time.OffsetDateTime,

    @field:Valid
    @field:JsonProperty("makeValue", required = true) val makeValue: java.math.BigDecimal,

    @field:Valid
    @field:JsonProperty("takeValue", required = true) val takeValue: java.math.BigDecimal
) {

}

