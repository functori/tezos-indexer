package org.rarible.tezos.indexer.filters

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

data class GetFTBalanceFilter(
    val contract: String,
    val owner: String,
    val tokenId: String?
)
