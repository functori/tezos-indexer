package com.rarible.protocol.tezos.api.model.activities

import com.fasterxml.jackson.annotation.JsonProperty
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTActivityDTO.blockHash
import com.rarible.protocol.tezos.api.util.tzkt.models.NFTActivityDTO.blockNumber
import com.rarible.protocol.tezos.api.util.tzkt.models.TokenBalanceDTO.owner
import javax.validation.Valid

/**
 *
 * @param atType
 * @param owner
 * @param contract
 * @param tokenId
 * @param value;
 * @param transactionHash
 * @param blockHash
 * @param blockNumber
 * @param elt
 * @param from
 */
data class NftTransferActivityElt(

    @field:JsonProperty("@type", required = true)
    override val type: NFTActivityType,

    @field:Valid
    @field:JsonProperty("elt", required = true) val elt: NftBaseActivityElt,

    @field:JsonProperty("from", required = true) val from: String
): NftActivity(type)

