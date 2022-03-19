package com.rarible.protocol.tezos.listener.config

import com.rarible.core.kafka.KafkaMessage
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto

object KafkaEventFactory {

    private val EVENT_HEADERS = mapOf(
        "protocol.version" to "1.0.2" // TODO: Get from TezosEventTopicProvider.VERSION
    )

    fun kafkaMsg(dto: TezosOrderSafeEventDto): KafkaMessage<TezosOrderSafeEventDto> {
        return KafkaMessage(
            key = dto.orderId!!,
            value = dto,
            headers = EVENT_HEADERS
        )
    }

    fun kafkaMsg(dto: TezosActivitySafeDto): KafkaMessage<TezosActivitySafeDto> {
        return KafkaMessage(
            key = dto.orderType!!.id, // TODO: check this
            value = dto,
            headers = EVENT_HEADERS
        )
    }
}
