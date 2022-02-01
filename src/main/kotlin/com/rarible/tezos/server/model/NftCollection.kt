package com.rarible.tezos.server.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.tezos.server.model.NftCollectionFeature
import com.rarible.tezos.server.model.NftCollectionType
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

    @field:JsonProperty("id", required = true) val id: kotlin.String,

    @field:Valid
    @field:JsonProperty("type", required = true) val type: NftCollectionType,

    @field:JsonProperty("name", required = true) val name: kotlin.String,

    @field:Valid
    @field:JsonProperty("features", required = true) val features: kotlin.collections.List<NftCollectionFeature>,

    @field:JsonProperty("supports_lazy_mint", required = true) val supportsLazyMint: kotlin.Boolean,

    @field:JsonProperty("owner") val owner: kotlin.String? = null,

    @field:JsonProperty("symbol") val symbol: kotlin.String? = null,

    @field:JsonProperty("minters") val minters: kotlin.collections.List<kotlin.String>? = null
) {

}

