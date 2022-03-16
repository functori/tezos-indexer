package com.rarible.protocol.tezos.listener.handler

import com.rarible.core.daemon.sequential.ConsumerEventHandler
import org.slf4j.LoggerFactory

class ExternalEventHandlerWrapper<B>(
    private val internalHandler: ExternalEventHandler<B>
) : ExternalEventHandler<B>, ConsumerEventHandler<B> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override suspend fun handle(event: B) {
        try {
            internalHandler.handle(event)
        } catch (ex: Exception) {
            logger.error("Unexpected exception during handling internal event [{}]", event, ex)
        }
    }

}
