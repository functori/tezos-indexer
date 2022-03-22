package com.rarible.protocol.tezos.listener.handler.external

import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.protocol.tezos.convertOrderEvent
import com.rarible.protocol.tezos.core.config.CommonProperties
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.listener.config.KafkaEventFactory.kafkaMsg
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["listener.enable"], havingValue = "true", matchIfMissing = false)
class ExternalOrderEventHandler(
    val producer: RaribleKafkaProducer<TezosOrderSafeEventDto>,
    val commonProps: CommonProperties
) : ExternalEventHandler<TezosOrderEventDto> {

    override suspend fun handle(source: TezosOrderEventDto) {
        val event = convertOrderEvent(source)

        // TODO: filter ignorable contracts

        val msg = kafkaMsg(event)
        producer.send(msg).ensureSuccess()
    }
}
