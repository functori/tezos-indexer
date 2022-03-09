package org.rarible.tezos.client.tzkt.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.rarible.tezos.client.tzkt.db.jsonb

object NFTCollectionDTO : Table("\"nft_collections\"") {
    val id: Column<String> = text("id")
    val owner: Column<String> = text("collection_owner")
    val metadata: Column<String> = jsonb("metadata", String::class.java)
    val storage: Column<String> = jsonb("storage", String::class.java)
    val entrypoints: Column<ByteArray> = binary("entrypoints")
}
