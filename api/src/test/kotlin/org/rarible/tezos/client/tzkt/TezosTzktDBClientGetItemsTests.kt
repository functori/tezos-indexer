package org.rarible.tezos.client.tzkt

import com.rarible.protocol.tezos.api.controller.V01ApiController
import org.junit.jupiter.api.Test
import com.rarible.protocol.tezos.api.util.tzkt.db.TzKTDBClient
import com.rarible.protocol.tezos.api.util.tzkt.repositories.NFTItemsRepository
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.util.OldAPI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class TezosTzktDBClientGetItemsTests {

	val dbClient: TzKTDBClient = TzKTDBClient()

	@Test
	fun contextLoads() {
	}

	@Test
	fun getAllNFTItems(){
		val result = NFTItemsRepository.queryAllNFTItems(null,null,false,true,50,null);
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
	fun getNFTItem2() {
		val apiController = V01ApiController()
		val test_api = OldAPI()
		val item = apiController.getNftItemById2(itemId = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654", includeMeta = true)
		val item2 = test_api.getNftItem("KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654")
		println(item.toString())
		println(item2.toString())
		assert(item == ResponseEntity<NftItem>(item2, HttpStatus.OK))
	}



//	@Test
//	fun getNFTItemMeta(){
//		val result = NFTItemsRepository.queryNFTItemMeta("KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS:39879");
//		assert(result != null)
//	}


}
