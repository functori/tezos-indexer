package com.rarible.protocol.tezos.listener.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("listener")
data class TezosListenerProperties(
    val consumer: ExternalConsumerProperties
)

class ExternalConsumerProperties(
    val brokerReplicaSet: String,
    val workers: Map<String, Int>
)
