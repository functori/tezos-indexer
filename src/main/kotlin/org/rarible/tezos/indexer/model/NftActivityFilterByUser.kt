package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param atType 
 * @param types 
 * @param users 
 */
data class NftActivityFilterByUser(

    @field:JsonProperty("@type", required = true) val atType: NftActivityFilterByUser.AtType,

    @field:Valid
    @field:JsonProperty("types", required = true) val types: kotlin.collections.List<NftActivityFilterUserType>,

    @field:JsonProperty("users", required = true) val users: kotlin.collections.List<kotlin.String>
) {

    /**
    * 
    * Values: byUser
    */
    enum class AtType(val value: kotlin.String) {
    
        @JsonProperty("by_user") byUser("by_user");
    
    }

}

