package com.rarible.protocol.tezos.listener

import com.rarible.protocol.tezos.core.config.CommonConfiguration
import com.rarible.protocol.tezos.listener.handler.KafkaConsumerWorker
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(CommonConfiguration::class)
class TezosListenerApplication(
	private val kafkaConsumers: List<KafkaConsumerWorker<*>>
) : CommandLineRunner, AutoCloseable {

	override fun run(vararg args: String?) {
		kafkaConsumers.forEach { it.start() }
	}

	override fun close() {
		kafkaConsumers.forEach { it.close() }
	}
}

fun main(args: Array<String>) {
	runApplication<TezosListenerApplication>(*args)
}
