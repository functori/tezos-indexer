package org.rarible.tezos.client.tzkt.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import org.rarible.tezos.client.tzkt.db.jsonb
import java.time.Instant

object NFTItems : Table("\"nft_items\"") {
    val id: Column<String> = text("id")
    val contract: Column<String> = text("contract")
    val tokenId: Column<String> = text("tokenId")
    val creators: Column<String> = text("creators")
    val supply: Column<String> = text("supply")
    val burned: Column<String> = text("burned")
    val owners: Column<String> = text("owners")
    val date: Column<Instant> = timestamp("date")
    val mintedAt: Column<Instant> = timestamp("mintedAt")
    val meta: Column<String> = jsonb("meta", String::class.java)
}
