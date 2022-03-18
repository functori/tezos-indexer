package com.rarible.protocol.tezos.listener.handler

import com.rarible.core.kafka.KafkaMessage
import com.rarible.core.test.data.randomString
import com.rarible.core.test.wait.Wait
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.listener.AbstractListenerTest
import com.rarible.protocol.tezos.listener.randomTezosOrderDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ProxyExternalEventsFt : AbstractListenerTest() {

    @Test
    fun `should send msg to internal topic`() = runWithKafka {

        val id = UUID.randomUUID().toString()
        val order = randomTezosOrderDto()

        externalProducer.send(
            KafkaMessage(
                key = id,
                value = TezosOrderEventDto(
                    eventId = randomString(),
                    order = order
                )
            )
        ).ensureSuccess()

        Wait.waitAssert {
            val messages = findOrderUpdates(order.hash)
            Assertions.assertThat(messages).hasSize(1)
        }
    }

}
