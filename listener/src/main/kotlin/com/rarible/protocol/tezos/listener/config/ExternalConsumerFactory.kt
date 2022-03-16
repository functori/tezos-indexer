package com.rarible.protocol.tezos.listener.config

import com.rarible.core.daemon.DaemonWorkerProperties
import com.rarible.core.daemon.sequential.ConsumerWorker
import com.rarible.core.kafka.RaribleKafkaConsumer
import com.rarible.protocol.tezos.listener.handler.BatchedConsumerWorker
import com.rarible.protocol.tezos.listener.handler.ExternalEventHandler
import com.rarible.protocol.tezos.listener.handler.ExternalEventHandlerWrapper
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class ExternalConsumerFactory(
    private val meterRegistry: MeterRegistry
) {

    companion object {
        const val WRAPPED = "external"
    }

    fun <T> createExternalEventConsumer(
        consumer: RaribleKafkaConsumer<T>,
        handler: ExternalEventHandler<T>,
        daemon: DaemonWorkerProperties,
        workerCount: Int
    ): BatchedConsumerWorker<T> {
        val workers = mapOf(WRAPPED to 1)
        return createExternalBatchedConsumerWorker(consumer, handler, daemon, workers, WRAPPED)
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
