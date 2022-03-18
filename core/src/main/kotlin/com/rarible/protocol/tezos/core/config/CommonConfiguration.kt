package com.rarible.protocol.tezos.core.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(CommonProperties::class)
class CommonConfiguration {
}
