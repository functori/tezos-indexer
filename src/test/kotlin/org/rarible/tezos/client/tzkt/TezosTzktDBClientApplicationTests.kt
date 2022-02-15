package org.rarible.tezos.client.tzkt

import org.junit.jupiter.api.Test
import org.rarible.tezos.client.tzkt.db.TzKTDBClient
import org.rarible.tezos.client.tzkt.models.TokenBalances.owner
import org.rarible.tezos.client.tzkt.models.TokenBalances.tokenId
import org.rarible.tezos.client.tzkt.repositories.NFTActivityRepository
import org.rarible.tezos.client.tzkt.repositories.TokenRepository
import org.rarible.tezos.indexer.model.activities.NftActivity
import org.rarible.tezos.indexer.model.activities.NftMintBurnActivityElt
import org.rarible.tezos.indexer.model.activities.NftTransferActivityElt
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

	@Test
	fun getAllNFTActivities(){
		val typeList = listOf("BURN", "MINT", "TRANSFER")
		var checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryAllNFTActivities(typeList);
		assert(result.isNotEmpty())
		result.forEach{
			assert(checkTypeList.contains(it.type.type))
		}
	}

	@Test
	fun getNFTActivitiesByUser(){
		val user = "tz1Zs2WDRv9549rnHkjAjdMaWXVnreixXVKD"
		val burnAddress = "tz1burnburnburnburnburnburnburjAYjjX"
		val typeList = listOf("BURN", "MINT", "TRANSFER_FROM")
		val checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryNFTActivitiesByUser(listOf(user),  typeList);
		print(result)
		assert(result.isNotEmpty())
		result.forEach{
				assert(checkTypeList.contains(it.type.type))
				when(it.type.type){
					NftActivity.NFTActivityType.transfer -> {
						var transfer: NftTransferActivityElt = it.type as NftTransferActivityElt
						assert(transfer.from == user || transfer.elt.owner == user)
					}
					NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.burn -> {
						var activity: NftMintBurnActivityElt = it.type as NftMintBurnActivityElt
						assert(activity.owner == user || activity.owner == burnAddress || activity.owner == null)
					}
				}
		}
	}

}
