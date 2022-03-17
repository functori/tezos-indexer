package com.rarible.protocol.tezos.listener.config

import com.rarible.core.daemon.DaemonWorkerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("listener")
data class TezosListenerProperties(
    val consumer: ConsumerProperties,
    val producer: ProducerProperties,
    val monitoringWorker: DaemonWorkerProperties = DaemonWorkerProperties(),
    val externalTopic: String
)

data class ConsumerProperties(
    val brokerReplicaSet: String,
    val workers: Map<String, Int>
)

data class ProducerProperties(
    val brokerReplicaSet: String,
    val orderTopic: String
)
