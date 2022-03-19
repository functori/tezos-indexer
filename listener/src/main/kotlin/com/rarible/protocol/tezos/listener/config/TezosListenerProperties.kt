package com.rarible.protocol.tezos.listener.config

import com.rarible.core.daemon.DaemonWorkerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("listener")
data class TezosListenerProperties(
    val consumer: ConsumerProperties,
    val producer: ProducerProperties,
    val monitoringWorker: DaemonWorkerProperties = DaemonWorkerProperties()
)

data class ConsumerProperties(
    val brokerReplicaSet: String,
    val workers: Map<String, Int>,
    val orderTopic: String,
    val activityTopic: String
)

data class ProducerProperties(
    val brokerReplicaSet: String,
    val username: String? = null,
    val password: String? = null,
    val orderTopic: String,
    val activityTopic: String
)
