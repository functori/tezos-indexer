package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
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

