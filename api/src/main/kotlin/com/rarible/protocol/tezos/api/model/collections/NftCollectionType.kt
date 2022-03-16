package com.rarible.protocol.tezos.api.model.collections

import com.fasterxml.jackson.annotation.JsonProperty

/**
*
* Values: nFT,mT
*/
enum class NftCollectionType(val value: String) {

    @JsonProperty("NFT") nft("NFT"),

    @JsonProperty("MT") mt("MT");

}

