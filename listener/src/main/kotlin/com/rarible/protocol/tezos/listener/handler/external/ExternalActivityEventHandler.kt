package com.rarible.protocol.tezos.listener.handler.external

import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.protocol.tezos.convertActivityEvent
import com.rarible.protocol.tezos.convertOrderEvent
import com.rarible.protocol.tezos.core.config.CommonProperties
import com.rarible.protocol.tezos.core.model.TezosActivityEventDto
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.listener.config.KafkaEventFactory.kafkaMsg
import org.springframework.stereotype.Component

@Component
class ExternalActivityEventHandler(
    val producer: RaribleKafkaProducer<TezosActivitySafeDto>,
    val commonProps: CommonProperties
) : ExternalEventHandler<TezosActivityEventDto> {

    override suspend fun handle(source: TezosActivityEventDto) {
        val event = convertActivityEvent(source)

        // TODO: filter ignorable contracts

        val msg = kafkaMsg(event)
        producer.send(msg).ensureSuccess()
    }
}
