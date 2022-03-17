package com.rarible.protocol.tezos.listener.handler.external

import com.rarible.core.kafka.KafkaMessage
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.protocol.tezos.convertOrder
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.listener.config.KafkaEventFactory.orderEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class ExternalOrderEventHandler(
    val producer: RaribleKafkaProducer<TezosOrderSafeEventDto>
) : ExternalEventHandler<TezosOrderEventDto> {

    override suspend fun handle(event: TezosOrderEventDto) {
        val event = orderEvent(convertOrder(event))
        producer.send(event).ensureSuccess()
    }
}
