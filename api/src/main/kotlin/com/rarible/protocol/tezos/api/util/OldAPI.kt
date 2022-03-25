package com.rarible.protocol.tezos.api.util

import com.rarible.protocol.tezos.api.model.NftOwnership
import com.rarible.protocol.tezos.api.model.items.NftItem
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ApiClient
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ClientException
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ClientError
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ServerException
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ServerError
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.RequestConfig
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.RequestMethod
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ResponseType
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.Success

class OldAPI(basePath: String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty("org.openapitools.client.baseUrl", "https://tezos-api.rarible.org")
        }
    }

    private inline fun <reified T>get(path: String, query: MutableMap<String, List<String>>) : T {
        val headers: MutableMap<String, String> = mutableMapOf()
        val config = RequestConfig(RequestMethod.GET, "/v0.1$path", headers, query)
        val response = request<T>(config, null)
        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as T
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val error = response as ClientError<*>
                throw ClientException("Client error : ${error.statusCode} ${error.message.orEmpty()}", error.statusCode, response)
            }
            ResponseType.ServerError -> {
                val error = response as ServerError<*>
                throw ServerException("Server error : ${error.statusCode} ${error.message.orEmpty()}", error.statusCode, response)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getNftItem(id: String, includeMeta: Boolean?) : NftItem {
        val localVariableQuery = mutableMapOf<String, List<String>>()
            .apply {
                if (includeMeta != null) {
                    put("includeMeta", listOf(includeMeta.toString()))
                }
            }
        return get("/items/$id", localVariableQuery)
    }


    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getNftOwnership(id: String) : NftOwnership {
        return get("/ownerships/$id", mutableMapOf())
    }

}
