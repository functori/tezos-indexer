package com.rarible.protocol.tezos.api.util.tzkt.api.models
import com.rarible.protocol.tezos.api.model.Part
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import java.time.OffsetDateTime

data class MetaTzip21 (

    @Json(name = "name")
    // String or List<String>Array
    var name: Any?,

    @Json(name = "symbol")
    var symbol: String?,

    @Json(name = "decimals")
    // String or Int
    var decimals: Any?,

    @Json(name = "artifactUri")
    val artifactUri : String?,

    @Json(name = "displayUri")
    val displayUri : String?,

    @Json(name = "thumbnailUri")
    val thumbnailUri : String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "minter")
    val minter: String?,

    @Json(name = "creators")
    // List<Part> or List<Pair<String,Int>> or List<String> or String
    val creators : List<String>?,

    @Json(name = "isBooleanAmount")
    // Boolean or String
    val isBooleanAmount : Any?,

    @Json(name = "formats")
    // List<Format> or Format
    val formats: List<Format>?,

    @Json(name = "attributes")
    val attributes : List<Attribute>?,

    @Json(name = "tags")
    // List<String> or String
    val tags : List<String>?,

    @Json(name = "contributors")
    val contributors: List<String>?,

    @Json(name = "publishers")
    val publishers: List<String>?,

    @Json(name = "date")
    val date : OffsetDateTime?,

    @Json(name = "blockLevel")
    val blockLevel: Int?,

    @Json(name = "genres")
    val genres: List<String>?,

    @Json(name = "language")
    val language: String?,

    @Json(name = "rights")
    val rights: String?,

    @Json(name = "rightUri")
    val rightUri: String?,

    @Json(name = "isTransferable")
    // Bool or String
    val isTransferable: Boolean?,

    @Json(name = "shouldPreferSymbol")
    // Bool or String
    val shouldPreferSymbol: Boolean?,

    @Json(name = "royalties")
    // decimals: Int? and shares: List<Part> or List<Pair<String,(Int or String)>>
    val royalties : List<Part>?,

    @Json(name = "creatorRoyalty")
    val creatorRoyalty: Int?
)
