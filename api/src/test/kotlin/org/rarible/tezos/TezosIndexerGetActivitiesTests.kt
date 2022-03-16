package org.rarible.tezos

import org.junit.jupiter.api.Test
import com.rarible.protocol.tezos.api.util.tzkt.db.TzKTDBClient
import com.rarible.protocol.tezos.api.controller.V01ApiController
import com.rarible.protocol.tezos.api.model.ActivitySort
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilter
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterAll
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterAllType
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByCollection
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByItem
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterByUser
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterUserType
import org.springframework.boot.test.context.SpringBootTest
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
                com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST,
                100,
                "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
                NftActivityFilterAll( NftActivityFilter.Type.all, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER))
            )
    }

    @Test
    fun getActivitesByUser() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByUser(NftActivityFilter.Type.byUser, listOf(NftActivityFilterUserType.BURN, NftActivityFilterUserType.MINT, NftActivityFilterUserType.TRANSFERTO, NftActivityFilterUserType.TRANSFERFROM), listOf("tz1Zs2WDRv9549rnHkjAjdMaWXVnreixXVKD"))
        )
    }

    @Test
    fun getActivitesByCollection() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByCollection(NftActivityFilter.Type.byCollection, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER), "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton")
        )
    }

    @Test
    fun getActivitesByItem() {
        val apiController = V01ApiController()
        apiController.getNftActivities(
            com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST,
            100,
            "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",
            NftActivityFilterByItem(NftActivityFilter.Type.byItem, listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER), "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton", "486654")
        )
    }

}
