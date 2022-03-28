package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.AbstractApiTest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderControllerIt : AbstractApiTest() {

    @Test
    fun `should get order`() = runBlocking<Unit> {
        mockSerser.enqueue(MockResponse().setBody("""
            {
                "data": {
                    "marketplace_order_by_pk": {
                        "id": 1,
                        "cancelled": true,
                        "created_at": "2022-02-10T08:35:04+00:00",
                        "ended_at": "2022-02-10T13:01:54+00:00",
                        "fill": 0.000000000000000000000000000000000000,
                        "internal_order_id": "1000000",
                        "last_updated_at": "2022-02-10T13:01:54+00:00",
                        "make_contract": "KT1Q8JB2bdphCHhEBKc1PMsjArLPcAezGBVK",
                        "make_price": 0.010000000000000000000000000000000000,
                        "make_stock": 10.000000000000000000000000000000000000,
                        "make_token_id": "2",
                        "make_value": 10.000000000000000000000000000000000000,
                        "maker": "tz1XHhjLXQuG9rf9n7o1VbgegMkiggy1oktu",
                        "network": "mainnet",
                        "platform": "Objkt_v2",
                        "started_at": "2022-02-10T08:35:04+00:00",
                        "status": "CANCELLED"
                    }
                }
            }""".trimIndent()))

        val order = orderApi.getOrderById("1").awaitSingle()

        assertThat(order).isNotNull
    }

}
