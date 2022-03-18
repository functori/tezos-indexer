package com.rarible.protocol.tezos.listener.config

import com.rarible.core.daemon.DaemonWorkerProperties
import com.rarible.core.daemon.sequential.ConsumerWorker
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.protocol.tezos.listener.handler.BatchedConsumerWorker
import com.rarible.protocol.tezos.listener.handler.external.ExternalEventHandler
import com.rarible.protocol.tezos.listener.handler.external.ExternalEventHandlerWrapper
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class ConsumerFactory(
    private val meterRegistry: MeterRegistry
) {

    companion object {
        const val EXTERNAL = "external"
    }

    fun <T> createExternalEventConsumer(
        consumer: RaribleKafkaConsumer<T>,
        handler: ExternalEventHandler<T>,
        daemon: DaemonWorkerProperties,
        workers: Map<String, Int>
    ): BatchedConsumerWorker<T> {
        return createExternalBatchedConsumerWorker(consumer, handler, daemon, workers, EXTERNAL)
    }

    fun <T> createExternalBatchedConsumerWorker(
        consumer: RaribleKafkaConsumer<T>,
        handler: ExternalEventHandler<T>,
        daemonWorkerProperties: DaemonWorkerProperties,
        workers: Map<String, Int>,
        entityType: String
    ): BatchedConsumerWorker<T> {
        val workerCount = workers.getOrDefault(entityType, 1)
        val workerSet = (1..workerCount).map {
            ConsumerWorker(
                consumer = consumer,
                properties = daemonWorkerProperties,
                eventHandler = ExternalEventHandlerWrapper(handler),
                meterRegistry = meterRegistry,
                workerName = "external-${entityType}-$it"
            )
        }
        return BatchedConsumerWorker(workerSet)
    }
}
