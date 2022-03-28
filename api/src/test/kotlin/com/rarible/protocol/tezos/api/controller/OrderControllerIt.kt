package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.AbstractApiTest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderControllerIt : AbstractApiTest() {

    @Test
    fun `should get order`() = runBlocking<Unit> {
        val order = orderApi.getOrderById("1").awaitSingle()

        assertThat(order).isNotNull
    }

}
