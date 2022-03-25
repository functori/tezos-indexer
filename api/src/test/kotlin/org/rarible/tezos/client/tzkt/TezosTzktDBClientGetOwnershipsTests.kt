package org.rarible.tezos.client.tzkt

import com.rarible.protocol.tezos.api.controller.V01ApiController
import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.util.OldAPI
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class TezosTzktDBClientGetOwnershipsTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun getNftOwnershipById(){
        val apiController = V01ApiController()
        val oldAPI = OldAPI()
        val ownershipId = "KT1RJ6PbjHpwc3M5rw5s2Nbmefwbuwbdxton:486654:tz1R8Ms9rERJmWKoCWoz2yx4Eg8Ldz4pruLy"
//        val ownershipId = "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS:36492:tz1iqZvuaCHED4RYmr8XuaR8bF168T4nvLZW"
        val ownership = apiController.getNftOwnershipById2(ownershipId).body
        val ownership2 = oldAPI.getNftOwnership(ownershipId)
        assert(ownership?.id == ownership2.id)
        assert(ownership?.contract == ownership2.contract)
        assert(ownership?.tokenId == ownership2.tokenId)
        assert(ownership?.owner == ownership2.owner)
        assert(ownership?.creators == ownership2.creators)
        assert(ownership?.value == ownership2.value)
        assert(ownership?.lazyValue == ownership2.lazyValue)
//        This seems wrong on OCaml backend
//        assert(ownership?.createdAt == ownership2.createdAt)
//        assert(ownership?.date == ownership2.date)
    }

}