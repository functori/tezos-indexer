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

class TezosTzktDBClientGetActivitiesTests {

	val dbClient: TzKTDBClient = TzKTDBClient()

	@Test
	fun contextLoads() {
	}

	@Test
	fun getAllNFTActivities(){
		val typeList = listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER)
		var checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryAllNFTActivities(typeList, "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",51, com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST);
		assert(result.isNotEmpty())
		result.forEach{
			println(it)
			assert(checkTypeList.contains(it.type.type))
		}
	}

	@Test
	fun getNFTActivitiesByUser(){
		val user = "tz1Zs2WDRv9549rnHkjAjdMaWXVnreixXVKD"
		val burnAddress = "tz1burnburnburnburnburnburnburjAYjjX"
		val typeList = listOf(NftActivityFilterUserType.TRANSFERFROM, NftActivityFilterUserType.TRANSFERTO, NftActivityFilterUserType.BURN, NftActivityFilterUserType.MINT )
		val checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryNFTActivitiesByUser(listOf(user), typeList, "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",51, com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST);
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
						assert(activity.owner == user || activity.owner == burnAddress)
					}
				}
		}
	}

	@Test
	fun getNFTActivitiesByItem(){
		val contract = "KT1L7GvUxZH5tfa6cgZKnH6vpp2uVxnFVHKu"
		val tokenId = "3796"
		val typeList = listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER)
		val checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryNFTActivitiesByItem(contract, tokenId, typeList, "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",51, com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST);
		print(result)
		assert(result.isNotEmpty())
		result.forEach{
			assert(checkTypeList.contains(it.type.type))
			when(it.type.type){
				NftActivity.NFTActivityType.transfer -> {
					var transfer: NftTransferActivityElt = it.type as NftTransferActivityElt
					assert(transfer.elt.contract == contract && transfer.elt.tokenId == tokenId)
				}
				NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.burn -> {
					var activity: NftMintBurnActivityElt = it.type as NftMintBurnActivityElt
					assert(activity.contract == contract && activity.tokenId == tokenId )
				}
			}
		}
	}

	@Test
	fun getNFTActivitiesByCollection(){
		val contract = "KT1L7GvUxZH5tfa6cgZKnH6vpp2uVxnFVHKu"
		val typeList = listOf(NftActivityFilterAllType.BURN, NftActivityFilterAllType.MINT, NftActivityFilterAllType.TRANSFER)
		val checkTypeList = listOf(NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.transfer, NftActivity.NFTActivityType.burn)
		val result = NFTActivityRepository.queryNFTActivitiesByItem(contract, null, typeList, "${Instant.parse("2022-01-28T23:22:58Z").toEpochMilli()}_opHX6rj8F4VUtVAeEZavyPus6FcZM6CDDCnWJuQ2B7XkhuzN4F9",51, com.rarible.protocol.tezos.api.model.ActivitySort.LATESTFIRST);
		print(result)
		assert(result.isNotEmpty())
		result.forEach{
			assert(checkTypeList.contains(it.type.type))
			when(it.type.type){
				NftActivity.NFTActivityType.transfer -> {
					var transfer: NftTransferActivityElt = it.type as NftTransferActivityElt
					assert(transfer.elt.contract == contract)
				}
				NftActivity.NFTActivityType.mint, NftActivity.NFTActivityType.burn -> {
					var activity: NftMintBurnActivityElt = it.type as NftMintBurnActivityElt
					assert(activity.contract == contract)
				}
			}
		}
	}

}
