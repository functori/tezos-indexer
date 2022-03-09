package org.rarible.tezos.indexer.model.collections

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: aPPROVEFORALL,sETURIPREFIX,bURN,mINTWITHADDRESS,sECONDARYSALEFEES,mINTANDTRANSFER
*/
enum class NftCollectionFeature(val value: kotlin.String) {

    @JsonProperty("APPROVE_FOR_ALL") aPPROVEFORALL("APPROVE_FOR_ALL"),

    @JsonProperty("SET_URI_PREFIX") sETURIPREFIX("SET_URI_PREFIX"),

    @JsonProperty("BURN") bURN("BURN"),

    @JsonProperty("MINT_WITH_ADDRESS") mINTWITHADDRESS("MINT_WITH_ADDRESS"),

    @JsonProperty("SECONDARY_SALE_FEES") sECONDARYSALEFEES("SECONDARY_SALE_FEES"),

    @JsonProperty("MINT_AND_TRANSFER") mINTANDTRANSFER("MINT_AND_TRANSFER");

}

