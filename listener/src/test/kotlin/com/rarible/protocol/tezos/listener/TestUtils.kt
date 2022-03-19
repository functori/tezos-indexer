package com.rarible.protocol.tezos.listener

import com.rarible.core.common.nowMillis
import com.rarible.core.test.data.randomBigDecimal
import com.rarible.core.test.data.randomBigInt
import com.rarible.core.test.data.randomInt
import com.rarible.core.test.data.randomLong
import com.rarible.core.test.data.randomString
import com.rarible.protocol.tezos.dto.AssetDto
import com.rarible.protocol.tezos.dto.FTAssetTypeDto
import com.rarible.protocol.tezos.dto.NFTAssetTypeDto
import com.rarible.protocol.tezos.dto.OrderActTypeDto
import com.rarible.protocol.tezos.dto.OrderActivityListDto
import com.rarible.protocol.tezos.dto.OrderActivityMatchDto
import com.rarible.protocol.tezos.dto.OrderActivityMatchTypeDto
import com.rarible.protocol.tezos.dto.OrderActivitySideMatchDto
import com.rarible.protocol.tezos.dto.OrderActivitySideTypeDto
import com.rarible.protocol.tezos.dto.OrderDto
import com.rarible.protocol.tezos.dto.OrderRaribleV2DataV1Dto
import com.rarible.protocol.tezos.dto.OrderStatusDto
import com.rarible.protocol.tezos.dto.PartDto
import com.rarible.protocol.tezos.dto.XTZAssetTypeDto
import java.math.BigInteger


fun randomTezosOrderDto() = randomTezosOrderDto(randomTezosAssetNFT(), randomString(), randomTezosAssetXtz())

fun randomTezosOrderDto(make: AssetDto, maker: String, take: AssetDto): OrderDto {
    return OrderDto(
        maker = maker,
        taker = randomString(),
        make = make,
        take = take,
        fill = randomBigInt(),
        makeStock = randomBigInt(),
        cancelled = false,
        salt = randomBigInt(32),
        data = OrderRaribleV2DataV1Dto(randomString(), listOf(randomTezosPartDto()), listOf(randomTezosPartDto())),
        signature = randomString(16),
        createdAt = nowMillis(),
        lastUpdateAt = nowMillis(),
        hash = randomString(32),
        makeBalance = randomBigInt(),
        start = randomInt(),
        end = randomInt(),
        priceHistory = listOf(),
        makerEdpk = randomString(),
        takerEdpk = randomString(),
        status = OrderStatusDto.ACTIVE,
        type = OrderDto.Type.RARIBLE_V2
    )
}

fun randomTezosOrderListActivity(): OrderActTypeDto {
    return OrderActTypeDto(
        id = randomString(),
        date = nowMillis(),
        source = "RARIBLE",
        type = OrderActivityListDto(
            hash = randomString(16),
            maker = randomString(),
            make = randomTezosAssetNFT(),
            take = randomTezosAssetFT(),
            price = randomBigDecimal()
        )
    )
}

fun randomTezosOrderActivityMatch(): OrderActTypeDto {
    return OrderActTypeDto(
        id = randomString(),
        date = nowMillis(),
        source = "RARIBLE",
        type = OrderActivityMatchDto(
            left = randomTezosOrderActivityMatchSide(),
            right = randomTezosOrderActivityMatchSide(),
            price = randomBigDecimal(),
            transactionHash = randomString(),
            blockHash = randomString(),
            blockNumber = randomBigInt(8),
            logIndex = randomInt(),
            type = OrderActivityMatchTypeDto.SELL
        )
    )
}

fun randomTezosOrderActivityMatchSide(): OrderActivitySideMatchDto {
    return OrderActivitySideMatchDto(
        maker = randomString(),
        hash = randomString(16),
        asset = randomTezosAssetFT(),
        type = OrderActivitySideTypeDto.values()[randomInt(OrderActivitySideTypeDto.values().size)]
    )
}

fun randomTezosAssetFT() = AssetDto(
    assetType = FTAssetTypeDto(randomString()),
    value = randomBigDecimal()
)

fun randomTezosAssetXtz() = AssetDto(
    assetType = XTZAssetTypeDto(),
    value = randomBigDecimal()
)

fun randomTezosAssetNFT() = randomTezosAssetNFT(randomTezosContract(), randomLong().toBigInteger())
fun randomTezosAssetNFT(contract: String, tokenId: BigInteger): AssetDto {
    return AssetDto(
        assetType = NFTAssetTypeDto(contract, tokenId),
        value = randomBigDecimal()
    )
}

fun randomTezosPartDto() = randomTezosPartDto(randomString())
fun randomTezosPartDto(account: String) = PartDto(account, randomInt())
fun randomTezosContract() = randomString(12)
