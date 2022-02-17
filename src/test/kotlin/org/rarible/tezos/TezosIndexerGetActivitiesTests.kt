package org.rarible.tezos

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.indexer.api.NotFoundException
import org.rarible.tezos.indexer.api.V01ApiController
import org.rarible.tezos.indexer.model.ActivitySort
import org.rarible.tezos.indexer.model.activities.ActivityType
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
class TezosIndexerGetActivitiesTests {

    val dbClient: TzKTDBClient = TzKTDBClient()

    @Test
    fun contextLoads() {
    }

    @Test
    fun getAllActivites() {
        val apiController = V01ApiController()
            apiController.getNftActivities(
                ActivitySort.LATESTFIRST,
                100,
                "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
                NftActivityFilterAll( NftActivityFilter.Type.all, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER))
            )
    }

    @Test
    fun getActivitesByUser() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByUser(NftActivityFilter.Type.byUser, listOf(NftActivityFilterUserType.BURN, NftActivityFilterUserType.MINT, NftActivityFilterUserType.TRANSFERTO, NftActivityFilterUserType.TRANSFERFROM), listOf("tz1Zs2WDRv9549rnHkjAjdMaWXVnreixXVKD"))
        )
    }

    @Test
    fun getActivitesByCollection() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByCollection(NftActivityFilter.Type.byCollection, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER), "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton")
        )
    }

    @Test
    fun getActivitesByItem() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByItem(NftActivityFilter.Type.byItem, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER), "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton", "486654")
        )
    }

}
