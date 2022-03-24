package com.rarible.protocol.tezos.api.util.tzkt.api.utils

import com.rarible.protocol.tezos.api.model.Part
import com.rarible.protocol.tezos.api.util.tzkt.api.models.MichelineParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.models.SortParameter
import com.rarible.protocol.tezos.api.util.tzkt.api.services.BigMapsApi
import com.rarible.protocol.tezos.api.util.tzkt.api.services.TokensApi
import java.io.InvalidClassException

fun getRoyalties(api: BigMapsApi, contract: String, tokenId: String): Pair<List<Part>, Boolean> {
    val bigMapKey = when (contract) {
        // Rarible
        "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS" -> {
            var bm = api.bigMapsGetKey(id = 55541, tokenId, null)
            // bm.value to list of part
            return Pair(listOf(), true)
        }

        // Hen
        "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton" -> {
            api.bigMapsGetKey(id = 522, tokenId, null)
            // bm.value to list of part
            return Pair(listOf(), false)
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
        var fst = royalties.first.sortedWith(compareByDescending { it.value })[0]
        fst.value = 10000
        return fst
    }


}