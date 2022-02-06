package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.apis.AccountsApi
import org.rarible.tezos.client.tzkt.filters.AnyAllFilterImpl
import org.rarible.tezos.client.tzkt.filters.EqualityFilterImpl
import org.rarible.tezos.client.tzkt.models.Account
import org.rarible.tezos.client.tzkt.models.AccountTypeParameter
import org.rarible.tezos.client.tzkt.models.AccountTypes
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.type.filter.TypeFilter

class TezosTzktClientApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun equalityFilterTest(){
		var equalityFilterImpl = EqualityFilterImpl()
		equalityFilterImpl.setEQ("user")
		assert(equalityFilterImpl.getFilter() == ".eq")
		assert(equalityFilterImpl.getFilterValue() == "user")
		assert(equalityFilterImpl.ne == null)
		equalityFilterImpl.setNE("contract")
		assert(equalityFilterImpl.getFilter() == ".ne")
		assert(equalityFilterImpl.getFilterValue() == "contract")
		assert(equalityFilterImpl.eq == null)
	}

	@Test
	fun anyAllFilterTest(){
		var anyAllFilter = AnyAllFilterImpl()
		anyAllFilter.setAllList(listOf("1","2"))
		assert(anyAllFilter.getFilter() == ".all")
		assert(anyAllFilter.getFilterValue() == "1,2")
		anyAllFilter.setAnyList(listOf("3","4"))
		assert(anyAllFilter.getFilter() == ".any")
		assert(anyAllFilter.getFilterValue() == "3,4")
	}

	/**
	 * ACCOUNTS API TESTS
	 */
	@Test
	fun getAccountsWithoutFilters() {
		var accountsApi = AccountsApi();
		var accounts =	accountsApi.accountsGet(null,null,null,null,null,null,null,null,null,null);
		accounts.forEach { it::class.java == Account::class.java }
	}

	@Test
	fun getAccountsWithAllFilters() {
		var accountsApi = AccountsApi();
		var equalityFilterImpl = EqualityFilterImpl()
		equalityFilterImpl.setEQ("user")
		var accountTypeFilter = AccountTypeParameter()
		accountTypeFilter.equalityFilterImpl = equalityFilterImpl
		var accounts =	accountsApi.accountsGet(accountTypeFilter,null,null,null,null,null,null,null,null,null);
		println(accounts)
	}

}
