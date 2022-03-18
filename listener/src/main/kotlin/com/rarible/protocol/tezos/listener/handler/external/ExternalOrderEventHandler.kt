package com.rarible.protocol.tezos.listener.handler.external

import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.protocol.tezos.convertOrderEvent
import com.rarible.protocol.tezos.core.config.CommonProperties
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.listener.config.KafkaEventFactory.kafkaMsg
import org.springframework.stereotype.Component

@Component
class ExternalOrderEventHandler(
    val producer: RaribleKafkaProducer<TezosOrderSafeEventDto>,
    val commonProps: CommonProperties
) : ExternalEventHandler<TezosOrderEventDto> {

    override suspend fun handle(event: TezosOrderEventDto) {
        val event = convertOrderEvent(event)

        // TODO: filter ignorable contracts

        val msg = kafkaMsg(event)
        producer.send(msg).ensureSuccess()
    }
}
