package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.apis.AccountsApi
import org.rarible.tezos.client.tzkt.models.AccountTypeParameter
import org.rarible.tezos.client.tzkt.models.AccountTypes
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
		var accounts =	accountsApi.accountsGet(AccountTypeParameter(AccountTypes.CONTRACT.value),null,null,null,null,null,null,null,null,null);
		println(accounts)
	}

}
