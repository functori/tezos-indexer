package com.rarible.protocol.tezos.api

import com.rarible.protocol.tezos.indexer.client.FixedTezosIndexerServiceUriProvider
import com.rarible.protocol.tezos.indexer.client.NoopWebClientCustomizer
import com.rarible.protocol.tezos.indexer.client.OrderControllerApi
import com.rarible.protocol.tezos.indexer.client.TezosIndexerClientFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers
import java.net.URI
import javax.annotation.PostConstruct

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "application.environment = test",
        "spring.cloud.consul.config.enabled = false",
        "spring.cloud.service-registry.auto-registration.enabled = false",
        "spring.cloud.discovery.enabled = false",
        "logging.logstash.tcp-socket.enabled = false"
    ]
)
@ActiveProfiles("test")
@Testcontainers
abstract class AbstractApiTest {

    @LocalServerPort
    protected var port: Int = 0

    protected lateinit var orderApi: OrderControllerApi

    @PostConstruct
    fun setup() {
        val urlProvider = FixedTezosIndexerServiceUriProvider(URI.create("http://127.0.0.1:$port"))
        val clientFactory = TezosIndexerClientFactory(urlProvider, NoopWebClientCustomizer())
        orderApi = clientFactory.createOrderApiClient()
    }

}
