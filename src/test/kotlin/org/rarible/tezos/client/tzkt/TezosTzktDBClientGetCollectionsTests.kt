package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.repositories.NFTActivityRepository
import org.rarible.tezos.client.tzkt.repositories.NFTCollectionsRepository
import org.rarible.tezos.client.tzkt.repositories.NFTItemsRepository
import org.rarible.tezos.client.tzkt.repositories.TokenRepository
import org.rarible.tezos.indexer.model.ActivitySort
import org.rarible.tezos.indexer.model.activities.NftActivity
import org.rarible.tezos.indexer.model.activities.NftMintBurnActivityElt
import org.rarible.tezos.indexer.model.activities.NftTransferActivityElt
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterAllType
import org.rarible.tezos.indexer.model.activities.filters.NftActivityFilterUserType
import java.math.BigDecimal
import java.time.Instant

class TezosTzktDBClientGetCollectionsTests {

	val dbClient: TzKTDBClient = TzKTDBClient()

	@Test
	fun contextLoads() {
	}

	@Test
	fun getAllNFTCollections(){
		val result = NFTCollectionsRepository.queryAllNFTCollections(50, null);
		assert(result.isNotEmpty())
		result.forEach{
			println(it)
		}
	}

	@Test
	fun getNFTItemsByCollection(){
		val result = NFTItemsRepository.queryNFTItemsByCollection("KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton",true,50,null);
		assert(result.isNotEmpty())
		result.forEach{
			println(it)
		}
	}

	@Test
	fun getNFTItemsByCreator(){
		val result = NFTItemsRepository.queryNFTItemsByCreator("KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton",true,50,null);
		assert(result.isNotEmpty())
		result.forEach{
			println(it)
		}
	}

	@Test
	fun getNFTItemsByOwner(){
		val result = NFTItemsRepository.queryNFTItemsByOwner("tz1burnburnburnburnburnburnburjAYjjX",true,1000,null);
		assert(result.isNotEmpty())
		result.forEach{
			println(it)
		}
	}

	@Test
	fun getNFTItem(){
		val result = NFTItemsRepository.queryNFTItem("KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654",true);
		assert(result != null)
	}

	@Test
	fun getNFTItemMeta(){
		val result = NFTItemsRepository.queryNFTItemMeta("KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS:39879");
		assert(result != null)
	}


}
