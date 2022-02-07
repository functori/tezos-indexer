package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.models.TokenBalances.tokenId
import org.rarible.tezos.client.tzkt.repositories.TokenRepository
import java.math.BigDecimal

class TezosTzktDBClientApplicationTests {

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
		val result = TokenRepository.queryTokenBalances(owner, contract, tokenId);
		assert(result == null)
	}

}
