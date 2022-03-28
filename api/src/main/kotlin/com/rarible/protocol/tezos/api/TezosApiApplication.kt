package com.rarible.protocol.tezos.api

import com.rarible.protocol.tezos.api.config.TezosApiConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(TezosApiConfiguration::class)
class TezosApiApplication

fun main(args: Array<String>) {

	System.setProperty("spring.profiles.active", "local")
	System.setProperty("spring.cloud.service-registry.auto-registration.enabled", "false")
	System.setProperty("spring.cloud.discovery.enabled", "false")
	System.setProperty("spring.cloud.consul.config.enabled", "false")
	System.setProperty("logging.logstash.tcp-socket.enabled", "false")
	System.setProperty("application.environment", "local")

	runApplication<TezosApiApplication>(*args)
}
