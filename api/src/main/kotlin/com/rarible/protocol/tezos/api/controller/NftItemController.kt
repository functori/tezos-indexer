package com.rarible.protocol.tezos.api.controller

import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.service.NftItemService
import com.rarible.protocol.tezos.api.util.tzkt.api.utils.nftItemDtoOfNftItem
import com.rarible.protocol.tezos.dto.NftItemDto
import com.rarible.protocol.tezos.dto.NftItemMetaDto
import com.rarible.protocol.tezos.dto.NftItemsDto
import com.rarible.protocol.tezos.indexer.api.controller.NftItemControllerApi
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class NftItemController(
    val itemService: NftItemService
    ) : NftItemControllerApi {
    override suspend fun getNftAllItems(
        lastUpdateFrom: String?,
        lastUpdateTo: String?,
        showDeleted: Boolean?,
        includeMeta: Boolean?,
        size: Int?,
        continuation: String?
    ): ResponseEntity<NftItemsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getNftItemById(itemId: String, includeMeta: Boolean?): ResponseEntity<NftItemDto> {
        return ResponseEntity.ok(nftItemDtoOfNftItem(itemService.getById(itemId)))
    }

    override suspend fun getNftItemMetaById(itemId: String): ResponseEntity<NftItemMetaDto> {
        TODO("Not yet implemented")
    }


}
