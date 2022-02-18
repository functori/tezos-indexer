package org.rarible.tezos.indexer.model.items

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.module.kotlin.contains
import org.rarible.tezos.client.tzkt.models.NFTActivities.type
import javax.validation.Valid

/**
 * 
 * @param name 
 * @param description 
 * @param attributes 
 * @param image 
 * @param animation 
 */
data class NftItemMeta(

    @field:JsonProperty("name", required = true) var name: String,

    @field:JsonProperty("description") var description: String? = null,

    @field:Valid
    @field:JsonProperty("attributes") var attributes: List<NftItemAttribute>? = null,

    @field:JsonProperty("image") var image: String? = null,

    @field:JsonProperty("animation") var animation: String? = null
) {
    companion object{
        val animationTypes: List<String> = listOf(
            "model/gltf+json",
            "model/gltf-binary",
            "audio/ogg",
            "audio/mpeg4-generic",
            "audio/mpeg",
            "audio/mp4",
            "video/mp4",
            "video/MP4V-ES",
            "video/mpeg4-generic",
            "video/quicktime",
            "video/H263",
            "video/H264"
        )
        fun parseNFTMetadata(metadata: String): NftItemMeta?{
            val nftMetadata = NftItemMeta("",null,null,null, null)
            val mapper = ObjectMapper()
            val rootNode: JsonNode = mapper.readTree(metadata)

            val fieldsIterator: Iterator<Map.Entry<String, JsonNode>> = rootNode.fields()
            while (fieldsIterator.hasNext()) {
                val (key, value) = fieldsIterator.next()
                when(key){
                    "name" -> nftMetadata.name = value.textValue()
                    "description" -> nftMetadata.description = value.textValue()
                    "attributes" -> {
                        nftMetadata.attributes =  (value as ArrayNode).map{ attribute ->
                            val currentAttribute = NftItemAttribute()
                            if(attribute.contains("name"))
                                currentAttribute.key = attribute.findValue("name").textValue()
                            if(attribute.contains("value"))
                                currentAttribute.value = attribute.findValue("value").textValue()
                            if(attribute.contains("type"))
                                currentAttribute.type = attribute.findValue("type").textValue()
                            if(attribute.contains("format"))
                                currentAttribute.format = attribute.findValue("format").textValue()
                             currentAttribute
                        }
                    }
                    "formats" -> {
                        (value as ArrayNode).map{ attribute ->
                            if(attribute.contains("mimeType")){
                                if(attribute.contains("uri")){
                                    if(attribute.findValue("mimeType").textValue() in animationTypes){
                                        nftMetadata.animation =  attribute.findValue("uri").textValue()
                                    }
                                }
                            }
                        }
                    }
                    "artifactUri" -> {
                        nftMetadata.image = value.textValue()
                    }
                }
            }
            return nftMetadata
        }
    }


}

