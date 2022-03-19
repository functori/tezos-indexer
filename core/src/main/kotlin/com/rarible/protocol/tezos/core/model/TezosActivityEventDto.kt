package com.rarible.protocol.tezos.core.model

import com.rarible.protocol.tezos.dto.NftActTypeDto
import com.rarible.protocol.tezos.dto.OrderActTypeDto

data class TezosActivityEventDto(
    val orderType: OrderActTypeDto?,
    val actType: NftActTypeDto?
)
