package com.rarible.protocol.tezos.listener

import com.rarible.core.kafka.KafkaMessage
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.test.ext.KafkaTest
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

@ContextConfiguration(classes = [TestTezosConfiguration::class])
@SpringBootTest(
    classes = [TezosListenerApplication::class],
    properties = [
        "application.environment = test",
        "spring.cloud.consul.config.enabled = false",
        "spring.cloud.service-registry.auto-registration.enabled = false",
        "spring.cloud.discovery.enabled = false",
        "logging.logstash.tcp-socket.enabled = false"
    ]
)
@ActiveProfiles("test")
@KafkaTest
@Testcontainers
abstract class AbstractListenerTest {

    @Autowired
    lateinit var externalProducer: RaribleKafkaProducer<TezosOrderEventDto>

    @Autowired
    lateinit var orderConsumer: RaribleKafkaConsumer<TezosOrderSafeEventDto>
    var orderEvents: Queue<KafkaMessage<TezosOrderSafeEventDto>>? = null
    private var orderJob: Deferred<Unit>? = null

    fun <T> runWithKafka(block: suspend CoroutineScope.() -> T): T = runBlocking {
        orderEvents = LinkedBlockingQueue()
        orderJob = async { orderConsumer.receiveAutoAck().collect { orderEvents?.add(it) } }

        val result = try {
            block()
        } finally {
            orderJob?.cancelAndJoin()
        }
        result
    }


    fun findOrderUpdates(orderId: String): List<KafkaMessage<TezosOrderSafeEventDto>> {
        return filterByValueType(orderEvents as Queue<KafkaMessage<Any>>, TezosOrderSafeEventDto::class.java)
            .filter { it.value.orderId == orderId }
    }

    private fun <T> filterByValueType(messages: Queue<KafkaMessage<Any>>, type: Class<T>): Collection<KafkaMessage<T>> {
        return messages.filter {
            type.isInstance(it.value)
        }.map {
            it as KafkaMessage<T>
        }
    }
}
