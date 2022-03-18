package com.rarible.protocol.tezos.listener

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.core.kafka.json.JsonSerializer
import com.rarible.core.test.ext.KafkaTestExtension
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.listener.config.TezosListenerProperties
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
class TestTezosConfiguration {

    @Autowired
    lateinit var properties: TezosListenerProperties

    @Bean
    fun meterRegistry(): MeterRegistry = SimpleMeterRegistry()

    @Bean
    fun testEthereumActivityEventProducer(): RaribleKafkaProducer<TezosOrderEventDto> {
        return RaribleKafkaProducer(
            clientId = "test.tezos.external.orders",
            valueSerializerClass = JsonSerializer::class.java,
            valueClass = TezosOrderEventDto::class.java,
            defaultTopic = properties.consumer.orderTopic,
            bootstrapServers = KafkaTestExtension.kafkaContainer.kafkaBoostrapServers()
        )
    }

    @Bean
    fun testOrderConsumer(): RaribleKafkaConsumer<TezosOrderSafeEventDto> {
        return RaribleKafkaConsumer(
            clientId = "test-tezos-order-consumer",
            consumerGroup = "test-tezos-order-group",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TezosOrderSafeEventDto::class.java,
            defaultTopic = properties.producer.orderTopic,
            bootstrapServers = KafkaTestExtension.kafkaContainer.kafkaBoostrapServers(),
            offsetResetStrategy = OffsetResetStrategy.EARLIEST
        )
    }
}
