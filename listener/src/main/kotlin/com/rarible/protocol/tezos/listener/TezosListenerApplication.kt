package com.rarible.protocol.tezos.listener

import com.rarible.protocol.tezos.listener.handler.KafkaConsumerWorker
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TezosListenerApplication(
	private val kafkaConsumers: List<KafkaConsumerWorker<*>>
) : CommandLineRunner {

	override fun run(vararg args: String?) {
		kafkaConsumers.forEach { it.start() }
	}
}

fun main(args: Array<String>) {
	runApplication<TezosListenerApplication>(*args)
}
