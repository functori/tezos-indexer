package com.rarible.protocol.tezos.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration

@ConstructorBinding
@ConfigurationProperties("api")
data class TezosApiProperties(
    val dipdup: DipDup
)

data class DipDup(
    val host: String
)
