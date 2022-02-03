package org.rarible.tezos.indexer.api

import org.rarible.tezos.indexer.model.ActivityType
import org.rarible.tezos.indexer.model.NftCollectionEvent
import org.rarible.tezos.indexer.model.NftItemEvent
import org.rarible.tezos.indexer.model.NftOwnershipEvent
import org.rarible.tezos.indexer.model.OrderEvent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class KafkaApiController() {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/kafka/activity"],
        produces = ["application/json"]
    )
    fun kafkaActivity(): ResponseEntity<ActivityType> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/kafka/collection"],
        produces = ["application/json"]
    )
    fun kafkaCollection(): ResponseEntity<NftCollectionEvent> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/kafka/item"],
        produces = ["application/json"]
    )
    fun kafkaItem(): ResponseEntity<NftItemEvent> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/kafka/order"],
        produces = ["application/json"]
    )
    fun kafkaOrder(): ResponseEntity<OrderEvent> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/kafka/ownership"],
        produces = ["application/json"]
    )
    fun kafkaOwnership(): ResponseEntity<NftOwnershipEvent> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
