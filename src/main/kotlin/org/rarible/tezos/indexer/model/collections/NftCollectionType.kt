package org.rarible.tezos.indexer.model.collections

import com.fasterxml.jackson.annotation.JsonProperty

/**
* 
* Values: nFT,mT
*/
enum class NftCollectionType(val value: kotlin.String) {

    @JsonProperty("NFT") nft("NFT"),

    @JsonProperty("MT") mt("MT");

}

