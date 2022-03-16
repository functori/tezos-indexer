package com.rarible.protocol.tezos.listener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TezosListenerApplication

fun main(args: Array<String>) {
	runApplication<TezosListenerApplication>(*args)
}
