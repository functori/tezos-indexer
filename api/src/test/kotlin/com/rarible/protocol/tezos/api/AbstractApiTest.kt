package com.rarible.protocol.tezos.api

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.indexer.client.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
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
@Import(TestApiConfiguration::class)
abstract class AbstractApiTest {

    @LocalServerPort
    protected var port: Int = 0

    protected lateinit var orderApi: OrderControllerApi
    protected lateinit var itemApi : NftItemControllerApi
//    protected lateinit var ownershipApi : NftOwnershipControllerApi

    @Autowired
    protected lateinit var mockSerser: MockWebServer

    @PostConstruct
    fun setup() {
        val urlProvider = FixedTezosIndexerServiceUriProvider(URI.create("http://127.0.0.1:$port"))
        val clientFactory = TezosIndexerClientFactory(urlProvider, NoopWebClientCustomizer())
        orderApi = clientFactory.createOrderApiClient()
        itemApi = clientFactory.createNftItemApiClient()
//        ownershipApi= clientFactory.createNFtOwnershipApiClient()
    }

    @AfterEach
    fun tearDown() {
        mockSerser.shutdown()
    }

}
