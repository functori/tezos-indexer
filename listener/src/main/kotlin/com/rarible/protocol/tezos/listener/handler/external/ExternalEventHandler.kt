package com.rarible.protocol.tezos.listener.handler.external

interface ExternalEventHandler<B> {
    suspend fun handle(event: B)
}
