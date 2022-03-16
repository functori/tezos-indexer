package com.rarible.protocol.tezos.listener.handler

interface KafkaConsumerWorker<T> : AutoCloseable {

    fun start()

}
