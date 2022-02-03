package org.rarible.tezos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TezosIndexerApplication

fun main(args: Array<String>) {
	runApplication<TezosIndexerApplication>(*args)
}
