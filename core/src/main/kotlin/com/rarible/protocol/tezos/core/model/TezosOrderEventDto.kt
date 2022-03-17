package com.rarible.protocol.tezos.core.model

import com.rarible.protocol.tezos.dto.OrderDto

data class TezosOrderEventDto(
    val eventId: String?,
    val order: OrderDto?
)
