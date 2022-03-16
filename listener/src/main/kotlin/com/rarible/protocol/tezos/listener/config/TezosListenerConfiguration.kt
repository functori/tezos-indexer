package com.rarible.protocol.tezos.listener.config

import com.rarible.core.application.ApplicationEnvironmentInfo
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.protocol.tezos.listener.handler.KafkaConsumerWorker
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [TezosListenerProperties::class])
class TezosListenerConfiguration(
    private val listenerProperties: TezosListenerProperties,
    applicationEnvironmentInfo: ApplicationEnvironmentInfo,
    private val consumerFactory: ExternalConsumerFactory
) {

//    @Bean
//    fun unionWrappedEventWorker(
//        consumer: RaribleKafkaConsumer<>,
//        handler: InternalEventHandler<>
//    ): KafkaConsumerWorker<> {
//        return consumerFactory.createWrappedEventConsumer(
//            consumer = consumer,
//            handler = handler,
//            daemon = listenerProperties.monitoringWorker,
//            workers = listenerProperties.consumer.workers
//        )
//    }

}
