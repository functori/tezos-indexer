package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.apis.AccountsApi
import org.springframework.boot.test.context.SpringBootTest

class TezosTzktClientApplicationTests {

	@Test
	fun contextLoads() {
	}

	/**
	 * ACCOUNTS API TESTS
	 */
	@Test
	fun getAccounts() {
		var accountsApi = AccountsApi();
		accountsApi.accountsGet(null,null,null,null,null,null,null,null,null,null);
	}

}
