package com.rarible.protocol.tezos.listener.handler

import com.rarible.core.kafka.KafkaMessage
import com.rarible.core.test.data.randomString
import com.rarible.core.test.wait.Wait
import com.rarible.protocol.tezos.core.model.TezosActivityEventDto
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.OrderActivityMatchDto
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
import com.rarible.protocol.tezos.listener.AbstractListenerTest
import com.rarible.protocol.tezos.listener.randomTezosOrderActivityMatch
import com.rarible.protocol.tezos.listener.randomTezosOrderDto
import com.rarible.protocol.tezos.listener.randomTezosOrderListActivity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ProxyExternalEventsFt : AbstractListenerTest() {

    @Test
    fun `should send order msg to internal topic`() = runWithKafka {

        val id = UUID.randomUUID().toString()
        val order = randomTezosOrderDto()

        externalOrderProducer.send(
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

    @Test
    fun `should send activity msg to internal topic`() = runWithKafka {

        val id = UUID.randomUUID().toString()
        val dto = randomTezosOrderListActivity()
        val event = TezosActivityEventDto(orderType = dto, actType = null)

        externalActivityProducer.send(
            KafkaMessage(
                key = id,
                value = event
            )
        ).ensureSuccess()

        Wait.waitAssert {
            val messages = findActivityUpdates(dto.id)
            Assertions.assertThat(messages).hasSize(1)
        }
    }

}
