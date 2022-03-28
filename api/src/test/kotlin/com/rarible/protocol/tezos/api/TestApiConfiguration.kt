package com.rarible.protocol.tezos.api

import com.expediagroup.graphql.client.spring.GraphQLWebClient
import com.rarible.protocol.tezos.api.config.TezosApiProperties
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import okhttp3.mockwebserver.MockWebServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class TestApiConfiguration {

    @Bean
    fun mockServer(): MockWebServer {
        return MockWebServer()
    }

    @Autowired
    lateinit var properties: TezosApiProperties

    @Bean
    fun meterRegistry(): MeterRegistry {
        return SimpleMeterRegistry()
    }

    @Bean
    @Primary
    fun testGraphglClient(mockServer: MockWebServer): GraphQLWebClient {
        return GraphQLWebClient(url = "http://127.0.0.1:${mockServer.port}")
    }
}
