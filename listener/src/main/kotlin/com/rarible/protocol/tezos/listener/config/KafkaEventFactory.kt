package com.rarible.protocol.tezos.listener.config

import com.rarible.core.kafka.KafkaMessage
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto

object KafkaEventFactory {

    private val ORDER_EVENT_HEADERS = mapOf(
        "protocol.union.order.event.version" to "1.0.2" // TODO: Get from TezosEventTopicProvider.VERSION
    )

    fun kafkaMsg(dto: TezosOrderSafeEventDto): KafkaMessage<TezosOrderSafeEventDto> {
        return KafkaMessage(
            key = dto.orderId!!,
            value = dto,
            headers = ORDER_EVENT_HEADERS
        )
    }
}
