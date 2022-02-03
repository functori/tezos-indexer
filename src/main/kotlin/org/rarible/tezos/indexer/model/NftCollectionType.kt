package org.rarible.tezos.indexer.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: nFT,mT
*/
enum class NftCollectionType(val value: kotlin.String) {

    @JsonProperty("NFT") nFT("NFT"),

    @JsonProperty("MT") mT("MT");

}

