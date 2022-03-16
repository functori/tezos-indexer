package com.rarible.protocol.tezos.api.util.tzkt.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object NFTActivityDTO : Table("\"nft_activities\"") {
    val type: Column<String> = text("type")
    val from: Column<String> = text("from")
    val to: Column<String> = text("to")
    val contract: Column<String> = text("contract")
    val tokenId: Column<String> = text("token_id")
    val value: Column<String> = text("value")
    val txHash: Column<String> = text("tx_hash")
    val blockHash: Column<String> = text("block_hash")
    val blockNumber: Column<Int> = integer("block_number")
    val date: Column<Instant> = timestamp("date")
}
