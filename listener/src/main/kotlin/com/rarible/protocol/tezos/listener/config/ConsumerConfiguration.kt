package com.rarible.protocol.tezos.listener.config

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.core.kafka.json.JsonDeserializer
import com.rarible.protocol.tezos.core.model.TezosActivityEventDto
import com.rarible.protocol.tezos.core.model.TezosOrderEventDto
import com.rarible.protocol.tezos.listener.handler.KafkaConsumerWorker
import com.rarible.protocol.tezos.listener.handler.external.ExternalEventHandler
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
@ConditionalOnProperty(name = ["listener.enable"], havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(value = [TezosListenerProperties::class])
class ConsumerConfiguration(
    private val listenerProperties: TezosListenerProperties,
    applicationEnvironmentInfo: ApplicationEnvironmentInfo,
    private val consumerFactory: ConsumerFactory
) {

    private val env = applicationEnvironmentInfo.name
    private val host = applicationEnvironmentInfo.host

    private val clientIdPrefix = "$env.$host.${UUID.randomUUID()}"

    @Bean
    fun externalOrderEventConsumer(): RaribleKafkaConsumer<TezosOrderEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.tezos-external-event-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TezosOrderEventDto::class.java,
            consumerGroup = consumerGroup("external.order"),
            defaultTopic = listenerProperties.consumer.orderTopic,
            bootstrapServers = listenerProperties.consumer.brokerReplicaSet,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST
        )
    }

    @Bean
    fun externalOrderEventWorker(
        consumer: RaribleKafkaConsumer<TezosOrderEventDto>,
        handler: ExternalEventHandler<TezosOrderEventDto>
    ): KafkaConsumerWorker<TezosOrderEventDto> {
        return consumerFactory.createExternalEventConsumer(
            consumer = consumer,
            handler = handler,
            daemon = listenerProperties.monitoringWorker,
            workers = listenerProperties.consumer.workers
        )
    }

    @Bean
    fun externalActivityEventConsumer(): RaribleKafkaConsumer<TezosActivityEventDto> {
        return RaribleKafkaConsumer(
            clientId = "$clientIdPrefix.tezos-external-event-consumer",
            valueDeserializerClass = JsonDeserializer::class.java,
            valueClass = TezosActivityEventDto::class.java,
            consumerGroup = consumerGroup("external.activity"),
            defaultTopic = listenerProperties.consumer.activityTopic,
            bootstrapServers = listenerProperties.consumer.brokerReplicaSet,
            offsetResetStrategy = OffsetResetStrategy.EARLIEST
        )
    }

    @Bean
    fun externalActivityEventWorker(
        consumer: RaribleKafkaConsumer<TezosActivityEventDto>,
        handler: ExternalEventHandler<TezosActivityEventDto>
    ): KafkaConsumerWorker<TezosActivityEventDto> {
        return consumerFactory.createExternalEventConsumer(
            consumer = consumer,
            handler = handler,
            daemon = listenerProperties.monitoringWorker,
            workers = listenerProperties.consumer.workers
        )
    }

    private fun consumerGroup(suffix: String): String {
        return "${env}.protocol.tezos.${suffix}"
    }

}
