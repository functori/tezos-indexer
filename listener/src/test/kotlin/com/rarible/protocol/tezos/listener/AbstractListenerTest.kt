package com.rarible.protocol.tezos.listener

import com.rarible.core.kafka.KafkaMessage
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.test.ext.KafkaTest
import com.rarible.protocol.tezos.core.model.TezosActivityEventDto
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
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
    lateinit var externalOrderProducer: RaribleKafkaProducer<TezosOrderEventDto>

    @Autowired
    lateinit var externalActivityProducer: RaribleKafkaProducer<TezosActivityEventDto>

    @Autowired
    lateinit var orderConsumer: RaribleKafkaConsumer<TezosOrderSafeEventDto>
    var orderEvents: Queue<KafkaMessage<TezosOrderSafeEventDto>>? = null
    private var orderJob: Deferred<Unit>? = null

    @Autowired
    lateinit var activityConsumer: RaribleKafkaConsumer<TezosActivitySafeDto>
    var activityEvents: Queue<KafkaMessage<TezosActivitySafeDto>>? = null
    private var activityJob: Deferred<Unit>? = null

    fun <T> runWithKafka(block: suspend CoroutineScope.() -> T): T = runBlocking {
        orderEvents = LinkedBlockingQueue()
        activityEvents = LinkedBlockingQueue()
        orderJob = async { orderConsumer.receiveAutoAck().collect { orderEvents?.add(it) } }
        activityJob = async { activityConsumer.receiveAutoAck().collect { activityEvents?.add(it) } }

        val result = try {
            block()
        } finally {
            orderJob?.cancelAndJoin()
            activityJob?.cancelAndJoin()
        }
        result
    }


    fun findOrderUpdates(orderId: String): List<KafkaMessage<TezosOrderSafeEventDto>> {
        return filterByValueType(orderEvents as Queue<KafkaMessage<Any>>, TezosOrderSafeEventDto::class.java)
            .filter { it.value.orderId == orderId }
    }

    fun findActivityUpdates(id: String): List<KafkaMessage<TezosActivitySafeDto>> {
        return filterByValueType(activityEvents as Queue<KafkaMessage<Any>>, TezosActivitySafeDto::class.java)
            .filter { it.value.orderType?.id == id }
    }

    private fun <T> filterByValueType(messages: Queue<KafkaMessage<Any>>, type: Class<T>): Collection<KafkaMessage<T>> {
        return messages.filter {
            type.isInstance(it.value)
        }.map {
            it as KafkaMessage<T>
        }
    }
}
