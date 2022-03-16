package com.rarible.protocol.tezos.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.protocol.tezos.api.model.collections.NftCollection
import javax.validation.Valid

/**
 *
 * @param eventId
 * @param collectionId
 * @param type
 * @param collection
 */
data class NftCollectionEvent(

    @field:JsonProperty("eventId", required = true) val eventId: String,

    @field:JsonProperty("collectionId", required = true) val collectionId: String,

    @field:JsonProperty("type", required = true) val type: NftCollectionEvent.Type,

    @field:Valid
    @field:JsonProperty("collection", required = true) val collection: NftCollection
) {

    /**
    *
    * Values: uPDATE
    */
    enum class Type(val value: String) {

        @JsonProperty("UPDATE") uPDATE("UPDATE");

    }

}

