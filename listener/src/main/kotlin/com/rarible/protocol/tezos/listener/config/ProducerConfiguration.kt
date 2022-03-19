package com.rarible.protocol.tezos.listener.config

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.core.kafka.RaribleKafkaProducer
import com.rarible.core.kafka.json.JsonSerializer
import com.rarible.protocol.tezos.dto.TezosActivitySafeDto
import com.rarible.protocol.tezos.dto.TezosOrderSafeEventDto
import com.rarible.protocol.tezos.dto.TezosOwnershipSafeEventDto
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["listener.enable"], havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(value = [TezosListenerProperties::class])
class ProducerConfiguration(
    private val properties: TezosListenerProperties,
    applicationEnvironmentInfo: ApplicationEnvironmentInfo
) {

    private val env = applicationEnvironmentInfo.name
    private val producerBrokerReplicaSet = properties.producer.brokerReplicaSet

    private fun properties(username: String?, password: String?): Map<String, String> {
        return if (username != null && password != null) mapOf(
            "security.protocol" to "SASL_PLAINTEXT",
            "sasl.mechanism" to "PLAIN",
            "sasl.jaas.config" to "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                    "username=\"$username\" password=\"$password\";"
        ) else emptyMap()
    }

    @Bean
    fun orderEventProducer(): RaribleKafkaProducer<TezosOrderSafeEventDto> {
        val orderTopic = properties.producer.orderTopic
        return createProducer("order", orderTopic, TezosOrderSafeEventDto::class.java)
    }

    @Bean
    fun activityEventProducer(): RaribleKafkaProducer<TezosActivitySafeDto> {
        val orderTopic = properties.producer.activityTopic
        return createProducer("activity", orderTopic, TezosActivitySafeDto::class.java)
    }

    private fun <T> createProducer(clientSuffix: String, topic: String, type: Class<T>): RaribleKafkaProducer<T> {
        return RaribleKafkaProducer(
            clientId = "${env}.protocol-tezos-service.${clientSuffix}",
            valueSerializerClass = JsonSerializer::class.java,
            valueClass = type,
            defaultTopic = topic,
            bootstrapServers = producerBrokerReplicaSet,
            properties = properties(properties.producer.username, properties.producer.password)
        )
    }
}
