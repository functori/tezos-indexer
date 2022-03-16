package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import com.rarible.protocol.tezos.api.util.tzkt.db.TzKTDBClient
import com.rarible.protocol.tezos.api.util.tzkt.repositories.NFTActivityRepository
import com.rarible.protocol.tezos.api.util.tzkt.repositories.TokenRepository
import com.rarible.protocol.tezos.api.model.ActivitySort
import com.rarible.protocol.tezos.api.model.activities.NftActivity
import com.rarible.protocol.tezos.api.model.activities.NftMintBurnActivityElt
import com.rarible.protocol.tezos.api.model.activities.NftTransferActivityElt
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterAllType
import com.rarible.protocol.tezos.api.model.activities.filters.NftActivityFilterUserType
import java.math.BigDecimal
import java.time.Instant

class TezosTzktDBClientGetBalanceTests {

	val dbClient: TzKTDBClient = TzKTDBClient()

	@Test
	fun contextLoads() {
	}

	@Test
	fun getTokenBalancesWithExistingBalance(){
		val owner = "KT1HbQepzV1nVGg8QVznG7z4RcHseD5kwqBn"
		val contract = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton"
		val tokenId = "486654"
		val result = TokenRepository.queryTokenBalances(contract, owner,tokenId);
		assert(result != null)
		assert(result!!.owner == owner)
		assert(result!!.contract == contract)
		assert(result!!.balance >= BigDecimal(0))
	}

	@Test
	fun getTokenBalancesWithNonExistingBalance(){
		val owner = "KT1HbQepzV1nVGg8QVznG7z4RcHseD5kwqBn"
		val contract = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton"
		val tokenId = "486654"
		val result = TokenRepository.queryTokenBalances(contract, owner, tokenId);
		assert(result != null)
		assert(result!!.owner == owner)
		assert(result!!.contract == contract)
		assert(result!!.balance >= BigDecimal(0))
	}
}
