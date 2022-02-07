package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.repositories.TokenRepository
import org.rarible.tezos.indexer.filters.GetFTBalanceFilter

class TezosTzktDBClientApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun getTokens(){
		var db = TzKTDBClient()
		val repo = TokenRepository()
		repo.queryTokens("","","");
	}

	@Test
	fun getTokenBalances(){
		var db = TzKTDBClient()
		val repo = TokenRepository()
		val filter = GetFTBalanceFilter("KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton","KT1HbQepzV1nVGg8QVznG7z4RcHseD5kwqBn","486654")
		val t = repo.queryTokenBalances(filter,"","");
		println(t)
	}

}
