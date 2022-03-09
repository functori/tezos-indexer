package org.rarible.tezos.indexer.model.collections

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param id 
 * @param type 
 * @param name 
 * @param features 
 * @param supportsLazyMint 
 * @param owner 
 * @param symbol 
 * @param minters 
 */
data class NftCollection(

    @field:JsonProperty("id", required = true) val id: String,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: NftCollectionType,

    @field:JsonProperty("name", required = true) val name: String,

    @field:Valid
    @field:JsonProperty("features", required = true) val features: List<NftCollectionFeature>,

    @field:JsonProperty("supports_lazy_mint", required = true) val supportsLazyMint: Boolean,

    @field:JsonProperty("owner") val owner: String? = null,

    @field:JsonProperty("symbol") val symbol: String? = null,

    @field:JsonProperty("minters") val minters: List<String>? = null
) {

}

