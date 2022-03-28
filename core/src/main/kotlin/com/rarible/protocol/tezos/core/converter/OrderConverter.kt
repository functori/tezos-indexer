package com.rarible.protocol.tezos.core.converter

import com.rarible.protocol.tezos.dto.AssetDto
import com.rarible.protocol.tezos.dto.NFTAssetTypeDto
import com.rarible.protocol.tezos.dto.OrderDto
import com.rarible.protocol.tezos.dto.OrderRaribleV2DataV1Dto
import com.rarible.protocol.tezos.dto.OrderStatusDto
import com.rarible.protocol.tezos.dto.XTZAssetTypeDto
import java.math.BigDecimal
import java.math.BigInteger
import java.time.OffsetDateTime

// Unfortunately graphql generator uses underscore instead of camel case
fun toOrderDto(source: net.dipdup.rarible.getorderbyid.marketplace_order): OrderDto {
    return OrderDto(
        data = OrderRaribleV2DataV1Dto("", listOf(), listOf()),
        maker = source.maker,
        makerEdpk = "",
        taker = null,
        takerEdpk = null,
        make = AssetDto(
                assetType = NFTAssetTypeDto(source.make_contract, BigInteger(source.make_token_id)),
                value = BigDecimal(source.make_value)
            ),
        take = AssetDto(
            assetType = XTZAssetTypeDto(),
            value = BigDecimal(source.make_price)
        ),
        fill = BigDecimal(source.fill).multiply(BigDecimal(10_000)).toBigInteger(),
        start = OffsetDateTime.parse(source.started_at).toEpochSecond().toInt(),
        end = OffsetDateTime.parse(source.ended_at).toEpochSecond().toInt(),
        makeStock = BigDecimal(source.make_stock).toBigInteger(),
        cancelled = source.cancelled,
        salt = BigInteger.ONE,
        signature = "",
        createdAt = OffsetDateTime.parse(source.created_at).toInstant(),
        lastUpdateAt = OffsetDateTime.parse(source.last_updated_at).toInstant(),
        hash = source.id,
        makeBalance = BigDecimal(source.fill).multiply(BigDecimal(10_000)).toBigInteger(),
        priceHistory = null,
        status = status(source.status),
        type = type(source.platform)
    )
}

fun status(source: String): OrderStatusDto {
    return when (source) {
        "FILLED" -> OrderStatusDto.FILLED
        "ACTIVE" -> OrderStatusDto.ACTIVE
        "CANCELLED" -> OrderStatusDto.CANCELLED
        "HISTORICAL" -> OrderStatusDto.HISTORICAL
        else -> OrderStatusDto.INACTIVE
    }
}

// TODO: adjust after adding new types
fun type(source: String): OrderDto.Type {
    return OrderDto.Type.RARIBLE_V2
}
