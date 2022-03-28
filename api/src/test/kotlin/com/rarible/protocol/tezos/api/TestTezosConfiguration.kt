package com.rarible.protocol.tezos.api

import com.rarible.protocol.tezos.api.config.TezosApiProperties
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
class TestTezosConfiguration {

    @Autowired
    lateinit var properties: TezosApiProperties

    @Bean
    fun meterRegistry(): MeterRegistry = SimpleMeterRegistry()

//    @TezosApiProperties

}
