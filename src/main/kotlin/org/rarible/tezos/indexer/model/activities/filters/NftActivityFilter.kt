package org.rarible.tezos.indexer.model.activities.filters

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param atType 
 * @param types 
 * @param users 
 * @param contract 
 * @param tokenId 
 */
abstract class NftActivityFilter(
    @field:JsonProperty("@type", required = true)
    open val type: Type
) {
    enum class Type(val value: String) {
        @JsonProperty("all") all("all"),
        @JsonProperty("by_collection") byCollection("by_collection"),
        @JsonProperty("by_item") byItem("by_item"),
        @JsonProperty("by_user") byUser("by_user"),
    }
}

