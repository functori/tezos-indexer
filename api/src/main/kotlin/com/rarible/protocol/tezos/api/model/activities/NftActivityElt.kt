package com.rarible.protocol.tezos.api.model.activities

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import javax.validation.Valid

/**
 *
 * @param id
 * @param date
 * @param source
 * @param type
 */
data class NftActivityElt(

    @field:JsonProperty("id", required = true) val id: String,

    @field:JsonProperty("date", required = true) val date: Instant,

    @field:JsonProperty("source", required = true) val source: String,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: NftActivity
) {

}

