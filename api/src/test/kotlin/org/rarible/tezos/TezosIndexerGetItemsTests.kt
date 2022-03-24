package org.rarible.tezos

import org.junit.jupiter.api.Test
import com.rarible.protocol.tezos.api.util.tzkt.db.TzKTDBClient
import com.rarible.protocol.tezos.api.controller.V01ApiController
import org.springframework.boot.test.context.SpringBootTest

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
