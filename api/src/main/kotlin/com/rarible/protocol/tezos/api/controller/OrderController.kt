package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.service.OrderService
import com.rarible.protocol.tezos.dto.ActivitySortDto
import com.rarible.protocol.tezos.dto.OrderDto
import com.rarible.protocol.tezos.dto.OrderIdsDto
import com.rarible.protocol.tezos.dto.OrderPaginationDto
import com.rarible.protocol.tezos.dto.OrderStatusDto
import com.rarible.protocol.tezos.indexer.api.controller.OrderControllerApi
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    val orderService: OrderService
) : OrderControllerApi {

    override suspend fun getOrderById(id: String): ResponseEntity<OrderDto> {
        return ResponseEntity.ok(orderService.getById(id))
    }

    override fun getOrderByIds(orderIdsDto: OrderIdsDto?): ResponseEntity<Flow<OrderDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrdersAll(
        origin: String?,
        sort: ActivitySortDto?,
        status: List<OrderStatusDto>?,
        size: Int?,
        continuation: String?
    ): ResponseEntity<OrderPaginationDto> {
        TODO("Not yet implemented")
    }


}
