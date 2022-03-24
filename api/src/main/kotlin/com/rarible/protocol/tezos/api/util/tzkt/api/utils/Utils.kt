package com.rarible.protocol.tezos.api.util.tzkt.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.convertValue
import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.util.tzkt.api.models.HenIssuer
import com.rarible.protocol.tezos.api.util.tzkt.api.models.SortParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.models.TzKtPart
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import java.io.InvalidClassException

fun getRoyalties(api: BigMapsApi, contract: String, tokenId: String): Pair<List<Part>, Boolean> {
    when (contract) {
        // Rarible
        "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS" -> {
            val bm = api.bigMapsGetKey(id = 55541, tokenId, null)
            println(bm.value)
            val mapper = ObjectMapper().registerModule(KotlinModule())
            var parts = mapper.convertValue<List<TzKtPart>>(bm.value!!)
            println(parts)
            val p = parts.map { Part(it.partAccount, it.partValue.toInt()) }
            return Pair(p, true)
        }

        // Hen
        "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton" -> {
            val bm = api.bigMapsGetKey(id = 522, tokenId, null)
            println(bm.value)
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val hen = mapper.convertValue<HenIssuer>(bm.value!!)
            println(hen)
            val parts =
                if (hen != null)
                    listOf(Part(account = hen.issuer, value = hen.royalties.toInt() * 10))
                else listOf()
            return Pair(parts, false)
        }
        // FXHASH
        // FROM metadata
        else -> throw InvalidClassException("Unknown royalties scheme")
    }
}

fun getCreator(api : TokensApi, contract : String, tokenId : String, royalties : Pair<List<Part>, Boolean>): Part {
    if (royalties.first.isEmpty()) {
        val transfers = api.tokensGetTokenTransfers(contract, tokenId, limit = 1, sort = SortParameter(asc = "timetamp"), offset = null)
        val owner = if (transfers[0].from == null) transfers[0].to?.address else transfers[0].from?.address
        return Part(owner!!, 10000)
    }
    else {
        val fst = royalties.first.sortedWith(compareByDescending { it.value })[0]
        return Part(account = fst.account, value = 10000)
    }


}