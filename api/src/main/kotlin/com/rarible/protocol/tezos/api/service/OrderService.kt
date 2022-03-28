package com.rarible.protocol.tezos.api.service

import com.expediagroup.graphql.client.spring.GraphQLWebClient
import com.rarible.protocol.tezos.core.converter.toOrderDto
import com.rarible.protocol.tezos.dto.OrderDto
import net.dipdup.rarible.GetOrderById
import org.springframework.stereotype.Component

@Component
class OrderService(
    val client: GraphQLWebClient
) {

    suspend fun getById(hash: String): OrderDto {
        val orderId = GetOrderById(GetOrderById.Variables("1"))
        val r2 = client.execute(orderId)
        val order = toOrderDto(r2.data!!.marketplace_order_by_pk!!)
        return order
    }

}
