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
class TezosIndexerApplicationTests {

    val dbClient: TzKTDBClient = TzKTDBClient()

    @Test
    fun contextLoads() {
    }

    /**
     * ********************
     * Get FT Balance tests
     * ********************
     */
    @Test
    fun getFA1FTBalanceWithExistingBalanceTest() {
        val apiController = V01ApiController()
        val result = apiController.ftBalance(
            "KT1G1cCRNBgQ48mVDjopHjEmTN5Sbtar8nn9",
            "tz1burnburnburnburnburnburnburjAYjjX",
            null
        )
		assert(result.statusCode.is2xxSuccessful)
		assert(result.body!!.balance >= BigDecimal(0))
	}

    @Test
    fun getFA1FTBalanceWithNonExistingBalanceTest() {
        val apiController = V01ApiController()
        assertThrows<NotFoundException> {
            apiController.ftBalance(
                "KT1G1cCRNBgQ48mVDjopHjEmTN5Sbtar89n9",
                "tz2LDByTzkpt7qBubspfyYiLYs6aKd321ztV",
                null
            )
        }
	}

    @Test
    fun getFA1FTBalanceWithWrongContractTest() {
        val apiController = V01ApiController()
        assertThrows<NotFoundException> {
            apiController.ftBalance(
				"KT1XXXXXXXn9",
				"tz2LDByTzkpt7qBubspfyYiLYs6aKd321ztV",
				null
			)
        }
    }

    @Test
    fun getFA1FTBalanceWithWrongAddressTest() {
        val apiController = V01ApiController()
        val result = apiController.ftBalance(
            "KT1G1cCRNBgQ48mVDjopHjEmTN5Sbtar8nn9",
            "tz2LDByTzkpt7qBubspiLYs6aKd321ztXXX",
            null
        )
        assert(result.statusCode.is2xxSuccessful)
        assert(result.body!!.balance >= BigDecimal(0))

    }

    @Test
    fun getFA2FTBalanceWithExistingBalanceTest() {
        val apiController = V01ApiController()
        val result = apiController.ftBalance("KT1JBNFcB5tiycHNdYGYCtR3kk6JaJysUCi8", "tz2LDByTzkpt7qBubspfyYiLYs6aKd321ztV", "0")
		assert(result.statusCode.is2xxSuccessful)
		assert(result.body!!.balance >= BigDecimal(0))
	}

    @Test
    fun getFA2FTBalanceWithNonExistingBalanceTest() {
        val apiController = V01ApiController()
        val result = apiController.ftBalance(
                "KT1JBNFcB5tiycHNdYGYCtR3kk6JaJysUCi8",
                "tz1burnburnburnburnburnburnburjAYjjX",
                "0"
            )
        assert(result.statusCode.is2xxSuccessful)
        assert(result.body!!.balance >= BigDecimal(0))
	}

	@Test
	fun getFA2FTBalanceWithWrongTokenIdTest() {
		val apiController = V01ApiController()
		assertThrows<NotFoundException> {
			apiController.ftBalance(
				"KT1G1cCRNBgQ48mVDjopHjEmTN5Sbtar8nn9",
				"tz2LDByTzkpt7qBubspiLYs6aKd321ztXXX",
				"X"
			)
		}
	}

	@Test
	fun getFA2FTBalanceWithNonExistingTokenIdTest() {
		val apiController = V01ApiController()
        assertThrows<NotFoundException> {
            apiController.ftBalance(
                "KT1G1cCRNBgQ48mVDjopHjEmTN5Sbtar8nn9",
                "tz2LDByTzkpt7qBubspfyYiLYs6aKd321ztV",
                "1"
            )
        }
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
