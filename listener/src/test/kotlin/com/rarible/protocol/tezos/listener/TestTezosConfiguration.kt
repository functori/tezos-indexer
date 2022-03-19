package com.rarible.protocol.tezos.listener

import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.core.kafka.json.JsonSerializer
import com.rarible.core.test.ext.KafkaTestExtension
import com.rarible.protocol.tezos.core.model.TezosActivityEventDto
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
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
    fun testOrderEventProducer(): RaribleKafkaProducer<TezosOrderEventDto> {
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

    @Bean
    fun testActivityEventProducer(): RaribleKafkaProducer<TezosActivityEventDto> {
        return RaribleKafkaProducer(
            clientId = "test.tezos.external.activities",
            valueSerializerClass = JsonSerializer::class.java,
            valueClass = TezosActivityEventDto::class.java,
            defaultTopic = properties.consumer.activityTopic,
            bootstrapServers = KafkaTestExtension.kafkaContainer.kafkaBoostrapServers()
        )
    }

    @Bean
    fun testActivityConsumer(): RaribleKafkaConsumer<TezosActivitySafeDto> {
        return RaribleKafkaConsumer(
            clientId = "test-tezos-activity-consumer",
            consumerGroup = "test-tezos-activity-group",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TezosActivitySafeDto::class.java,
            defaultTopic = properties.producer.activityTopic,
            bootstrapServers = KafkaTestExtension.kafkaContainer.kafkaBoostrapServers(),
            offsetResetStrategy = OffsetResetStrategy.EARLIEST
        )
    }
}
