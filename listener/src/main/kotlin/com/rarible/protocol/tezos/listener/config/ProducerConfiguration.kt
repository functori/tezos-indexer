package com.rarible.protocol.tezos.listener.config

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.kafka.json.JsonSerializer
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [TezosListenerProperties::class])
class ProducerConfiguration(
    private val properties: TezosListenerProperties,
    applicationEnvironmentInfo: ApplicationEnvironmentInfo
) {

    private val env = applicationEnvironmentInfo.name
    private val producerBrokerReplicaSet = properties.producer.brokerReplicaSet

    @Bean
    fun orderEventProducer(): RaribleKafkaProducer<TezosOrderSafeEventDto> {
        val orderTopic = properties.producer.orderTopic
        return createProducer("order", orderTopic, TezosOrderSafeEventDto::class.java)
    }

    private fun <T> createProducer(clientSuffix: String, topic: String, type: Class<T>): RaribleKafkaProducer<T> {
        return RaribleKafkaProducer(
            clientId = "${env}.protocol-tezos-service.${clientSuffix}",
            valueSerializerClass = JsonSerializer::class.java,
            valueClass = type,
            defaultTopic = topic,
            bootstrapServers = producerBrokerReplicaSet
        )
    }

}
