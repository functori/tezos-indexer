package org.openapitools

import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
@ComponentScan(basePackages = ["com.rarible.tezos.server", "com.rarible.tezos.server.api", "com.rarible.tezos.server.model"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
