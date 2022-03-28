package com.rarible.protocol.tezos.api.config

import com.expediagroup.graphql.client.spring.GraphQLWebClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TezosApiProperties::class)
class TezosApiConfiguration(
    val properties: TezosApiProperties
) {

    @Bean
    fun graphglClient(): GraphQLWebClient {
        return GraphQLWebClient(url = properties.dipdup.host)
    }

}
