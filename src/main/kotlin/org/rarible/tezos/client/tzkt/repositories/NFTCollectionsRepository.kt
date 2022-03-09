package org.rarible.tezos.client.tzkt.repositories

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.rarible.tezos.client.tzkt.models.NFTCollectionDTO
import org.rarible.tezos.client.tzkt.models.NFTItemDTO.owners
import org.rarible.tezos.indexer.model.collections.NftCollection
import org.rarible.tezos.indexer.model.collections.NftCollectionType

class NFTCollectionsRepository {
    companion object {

        class NFTCollectionsRepositoryException(msg: String, val code: Int) : Exception(msg)

        private const val defaultSize: Int = 50

        private fun processOwner(metadata: String): String {
            var owner = ""
            val mapper = ObjectMapper()
            val rootNode: JsonNode = mapper.readTree(metadata)

            val fieldsIterator: Iterator<Map.Entry<String, JsonNode>> = rootNode.fields()
            while (fieldsIterator.hasNext()) {
                val (key, value) = fieldsIterator.next()
                when (key) {
                    "owner" -> nftMetadata.name = value.textValue()
                }
            }
        }

    }

    private fun processItem(item: ResultRow): NftCollection? {
        return NftCollection(
            id = item[NFTCollectionDTO.id],
            owner = item[NFTCollectionDTO.owner],
            name = "",
            features = listOf(),
            minters = listOf(),
            supportsLazyMint = false,
            symbol = "",
            type = NftCollectionType.mt
        )
    }

    private fun applyFilters(query: Query, continuation: String?, size: Int?): Query {
        if (continuation != null) {
            query.andWhere {
                NFTCollectionDTO.id less continuation
            }
        }

        if (size != null) {
            if (size <= 0 || size > 1000) {
                throw NFTActivityRepository.Companion.NFTActivityRepositoryException(
                    "Size can't be <= 0 or > 1000",
                    500
                )
            }
            query.limit(size)
        } else {
            query.limit(defaultSize)
        }

        query.orderBy(Pair(NFTCollectionDTO.id, SortOrder.DESC))

        return query
    }

    fun queryAllNFTCollections(size: Int?, continuation: String?): List<NftCollection> {
        var result: MutableList<NftCollection> = mutableListOf()
        var query = NFTCollectionDTO.selectAll()

        query = applyFilters(query, continuation, size)

        transaction {
            query.forEach {
                var item: NftCollection? =
                    processItem(it) ?: throw NFTCollectionsRepositoryException(
                        "Could not parse activity: $it",
                        500
                    )
                result.add(item!!)
            }
        }
        return result
    }
//
//        fun queryNFTItemsByCollection(collection: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
//            var result: MutableList<NftItem> = mutableListOf()
//            var query =  NFTItemDTO.select {
//                NFTItemDTO.contract eq collection
//            }
//
//            query = applyFilters(query, null, null, continuation, size)
//
//            transaction {
//                query.forEach {
//                    var item: NftItem? =
//                        processItem(it, includeMeta) ?: throw NFTCollectionsRepositoryException(
//                            "Could not parse activity: $it",
//                            500
//                        )
//                    result.add(item!!)
//                }
//            }
//            return result
//        }
//
//        fun queryNFTItemsByCreator(creator: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
//            var result: MutableList<NftItem> = mutableListOf()
//            var query =  NFTItemDTO.select {
//                NFTItemDTO.creators like "%$creator%"
//            }
//
//            query = applyFilters(query, null, null, continuation, size)
//
//            transaction {
//                query.forEach {
//                    var item: NftItem? =
//                        processItem(it, includeMeta) ?: throw NFTCollectionsRepositoryException(
//                            "Could not parse activity: $it",
//                            500
//                        )
//                    result.add(item!!)
//                }
//            }
//            return result
//        }
//
//        fun queryNFTItemsByOwner(owner: String, includeMeta: Boolean?, size: Int?, continuation: String?): List<NftItem> {
//            var result: MutableList<NftItem> = mutableListOf()
//            var query =  NFTItemDTO.select {
//                NFTItemDTO.owners like "%$owner%"
//            }
//
//            query = applyFilters(query, null, null, continuation, size)
//
//            transaction {
//                query.forEach {
//                    var item: NftItem? =
//                        processItem(it, includeMeta) ?: throw NFTCollectionsRepositoryException(
//                            "Could not parse activity: $it",
//                            500
//                        )
//                    result.add(item!!)
//                }
//            }
//            return result
//        }
//
//        fun queryNFTItem(id: String, includeMeta: Boolean?): NftItem? {
//            var result: NftItem? = null
//            var query =  NFTItemDTO.select {
//                NFTItemDTO.id eq id
//            }
//
//            transaction {
//                query.forEach {
//                    var res = it
//                    if( res != null ){
//                        result =
//                            processItem(it, includeMeta) ?: throw NFTCollectionsRepositoryException(
//                                "Could not parse activity: $it",
//                                500
//                            )
//                    }
//
//                }
//            }
//            return result
//        }
//
//        fun queryNFTItemMeta(id: String): NftItemMeta? {
//            var result: NftItemMeta? = null
//            var query =  NFTItemDTO.slice(NFTItemDTO.meta).select {
//                NFTItemDTO.id eq id
//            }
//
//            transaction {
//                query.forEach {
//                    var res = it[NFTItemDTO.meta]
//                    if( res != null ){
//                        var item: NftItemMeta? =
//                            parseNFTMetadata(it[NFTItemDTO.meta]) ?: throw NFTCollectionsRepositoryException(
//                                "Could not parse activity: $it",
//                                500
//                            )
//                        result = item
//                    }
//                }
//            }
//            return result
//        }
}
}