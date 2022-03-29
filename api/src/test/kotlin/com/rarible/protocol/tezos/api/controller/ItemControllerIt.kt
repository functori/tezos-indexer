package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.AbstractApiTest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemControllerIt : AbstractApiTest() {

    @Test
    fun `should get item`() = runBlocking<Unit> {
        // Tokens RPC Response
        mockSerser.enqueue(MockResponse().setBody("""[
{
"id": 1885907,
"contract": {
"alias": "Rarible",
"address": "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS"
},
"tokenId": "36492",
"standard": "fa2",
"firstLevel": 2081025,
"firstTime": "2022-02-01T16:49:36Z",
"lastLevel": 2188650,
"lastTime": "2022-03-11T23:58:24Z",
"transfersCount": 4,
"balancesCount": 4,
"holdersCount": 4,
"totalMinted": "5",
"totalBurned": "0",
"totalSupply": "5",
"metadata": {
"name": "The adventures first, explanations take such a dreadful time.",
"formats": [
{
"uri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"fileName": "InShot_20220201_184632936.mp4",
"fileSize": "2385011",
"mimeType": "video/mp4"
}
],
"decimals": "0",
"attributes": [],
"artifactUri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"description": ""
}
}
]""".trimIndent()))

        // BigMap keys Response
        mockSerser.enqueue(MockResponse().setBody("""{
            "id": 17456250,
            "active": true,
            "hash": "exprub8wUtetbLuPijE4s3EuuSegup81xQhsvTLn47WzrsMVQaoACe",
            "key": "36492",
            "value": [
            {
                "partValue": "1000",
                "partAccount": "tz2BB5nTCB4cFUgdd9m19wuSRyP75qDtuVGi"
            }
            ],
            "firstLevel": 2081025,
            "lastLevel": 2081025,
            "updates": 1
        }""".trimIndent()))

        // Token Balances response

        mockSerser.enqueue(MockResponse().setBody("""[
{
"id": 7810008,
"account": {
"address": "tz2BB5nTCB4cFUgdd9m19wuSRyP75qDtuVGi"
},
"token": {
"id": 1885907,
"contract": {
"alias": "Rarible",
"address": "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS"
},
"tokenId": "36492",
"standard": "fa2",
"metadata": {
"name": "The adventures first, explanations take such a dreadful time.",
"formats": [
{
"uri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"fileName": "InShot_20220201_184632936.mp4",
"fileSize": "2385011",
"mimeType": "video/mp4"
}
],
"decimals": "0",
"attributes": [],
"artifactUri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"description": ""
}
},
"balance": "2",
"transfersCount": 4,
"firstLevel": 2081025,
"firstTime": "2022-02-01T16:49:36Z",
"lastLevel": 2188650,
"lastTime": "2022-03-11T23:58:24Z"
},
{
"id": 9086800,
"account": {
"address": "tz1akY3iVTn4qRA7CcLtMd52LAARc9YuHbdw"
},
"token": {
"id": 1885907,
"contract": {
"alias": "Rarible",
"address": "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS"
},
"tokenId": "36492",
"standard": "fa2",
"metadata": {
"name": "The adventures first, explanations take such a dreadful time.",
"formats": [
{
"uri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"fileName": "InShot_20220201_184632936.mp4",
"fileSize": "2385011",
"mimeType": "video/mp4"
}
],
"decimals": "0",
"attributes": [],
"artifactUri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"description": ""
}
},
"balance": "1",
"transfersCount": 1,
"firstLevel": 2180419,
"firstTime": "2022-03-09T01:51:54Z",
"lastLevel": 2180419,
"lastTime": "2022-03-09T01:51:54Z"
},
{
"id": 9182855,
"account": {
"address": "tz2QoaB4htit1wRipG1juHDVF5GtHkYw9mLL"
},
"token": {
"id": 1885907,
"contract": {
"alias": "Rarible",
"address": "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS"
},
"tokenId": "36492",
"standard": "fa2",
"metadata": {
"name": "The adventures first, explanations take such a dreadful time.",
"formats": [
{
"uri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"fileName": "InShot_20220201_184632936.mp4",
"fileSize": "2385011",
"mimeType": "video/mp4"
}
],
"decimals": "0",
"attributes": [],
"artifactUri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"description": ""
}
},
"balance": "1",
"transfersCount": 1,
"firstLevel": 2187835,
"firstTime": "2022-03-11T17:06:14Z",
"lastLevel": 2187835,
"lastTime": "2022-03-11T17:06:14Z"
},
{
"id": 9194593,
"account": {
"address": "tz1iqZvuaCHED4RYmr8XuaR8bF168T4nvLZW"
},
"token": {
"id": 1885907,
"contract": {
"alias": "Rarible",
"address": "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS"
},
"tokenId": "36492",
"standard": "fa2",
"metadata": {
"name": "The adventures first, explanations take such a dreadful time.",
"formats": [
{
"uri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"fileName": "InShot_20220201_184632936.mp4",
"fileSize": "2385011",
"mimeType": "video/mp4"
}
],
"decimals": "0",
"attributes": [],
"artifactUri": "ipfs://QmVZVtBuuw8FN9ehTQfcUtpH9WmBP2kkZPf2Z4MFX4KM5L/animation.mp4",
"description": ""
}
},
"balance": "1",
"transfersCount": 1,
"firstLevel": 2188650,
"firstTime": "2022-03-11T23:58:24Z",
"lastLevel": 2188650,
"lastTime": "2022-03-11T23:58:24Z"
}
]""".trimIndent()))
        val itemId = "KT18pVpRXKPY2c4U2yFEGSH3ZnhB2kL8kwXS:36492"
        val item = itemApi.getNftItemById(itemId,true).awaitSingle()
        println(item)
        assertThat(item).isNotNull
        assertThat(item.id).isEqualTo(itemId)
        assertThat(item.meta!!.name).isEqualTo("The adventures first, explanations take such a dreadful time.")
    }

}
