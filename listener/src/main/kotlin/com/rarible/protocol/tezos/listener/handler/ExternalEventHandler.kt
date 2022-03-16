package com.rarible.protocol.tezos.listener.handler

interface ExternalEventHandler<B> {

    suspend fun handle(event: B)

}
