package org.rarible.tezos.indexer.model.activities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param id 
 * @param date 
 * @param source 
 * @param type 
 */
open class NftActivity(
    @field:Valid
    @field:JsonProperty("@type", required = true) open val type: NFTActivityType
) {
    enum class NFTActivityType(val value: kotlin.String) {
        @JsonProperty("TRANSFER") transfer("TRANSFER"),
        @JsonProperty("BURN") burn("BURN"),
        @JsonProperty("MINT") mint("MINT")
    }
}

