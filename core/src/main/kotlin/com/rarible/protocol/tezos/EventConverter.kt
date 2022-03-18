package com.rarible.protocol.tezos

import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto

fun convertOrderEvent(source: TezosOrderEventDto): TezosOrderSafeEventDto {
    return TezosOrderSafeEventDto(
        eventId = source.eventId,
        order = source.order,
        orderId = source.order?.hash,
        type = TezosOrderSafeEventDto.Type.UPDATE
    )
}
