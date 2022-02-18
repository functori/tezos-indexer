package org.rarible.tezos

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.models.NFTItems.id
import org.rarible.tezos.indexer.api.NotFoundException
import org.rarible.tezos.indexer.api.V01ApiController
import org.rarible.tezos.indexer.model.ActivitySort
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilter
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterAll
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterAllType
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterByCollection
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterByItem
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterByUser
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterUserType
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.Instant

@SpringBootTest
class TezosIndexerGetItemsTests {

    val dbClient: TzKTDBClient = TzKTDBClient()

    @Test
    fun contextLoads() {
    }


    @Test
    fun getAllNFTItems() {
        val apiController = V01ApiController()
            apiController.getNftAllItems(
                lastUpdateFrom =  null,
                lastUpdateTo = null,
                showDeleted =  true,
                includeMeta = true,
                size = 100,
                continuation = null
           )
    }

    @Test
    fun getNFTItemsByOwner() {
        val apiController = V01ApiController()
        apiController.getNftItemsByOwner(owner = "tz1burnburnburnburnburnburnburjAYjjX", includeMeta = true, size = 100, continuation = null)
    }

    @Test
    fun getNFTItemsByCreator() {
        val apiController = V01ApiController()
        apiController.getNftItemsByOwner(owner = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton", includeMeta = true, size = 100, continuation = null)
    }

    @Test
    fun getNFTItemsByCollection() {
        val apiController = V01ApiController()
        apiController.getNftItemsByCollection(collection = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton", includeMeta = true, size = 100, continuation = null)
    }

    @Test
    fun getNFTItem() {
        val apiController = V01ApiController()
        apiController.getNftItemById(itemId = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654", includeMeta = true)
    }

    @Test
    fun getNFTItemMetadata() {
        val apiController = V01ApiController()
        apiController.getNftItemMetaById(itemId = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654")
    }

}
