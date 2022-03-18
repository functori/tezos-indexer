package com.rarible.protocol.tezos.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("common")
data class CommonProperties(
    val ignorableContracts: Set<String> = emptySet()
)

