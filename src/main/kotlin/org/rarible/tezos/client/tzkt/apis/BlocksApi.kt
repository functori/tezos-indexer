/**
 * TzKT API
 *
 * # Introduction  TzKT Explorer provides free REST API and WebSocket API for accessing detailed Tezos blockchain data and helps developers build more services and applications on top of Tezos. TzKT is an open-source project, so you can easily clone and build it and use it as a self-hosted service to avoid any risks of depending on third-party services.  TzKT API is available for the following Tezos networks with the following base URLs:  - Mainnet: `https://api.tzkt.io/` or `https://api.mainnet.tzkt.io/` ([view docs](https://api.tzkt.io))  - Granadanet: `https://api.granadanet.tzkt.io/` ([view docs](https://api.granadanet.tzkt.io))     - Hangzhou2net: `https://api.hangzhou2net.tzkt.io/` ([view docs](https://api.hangzhou2net.tzkt.io))  We also provide a staging environment for testing newest features and pre-updating client applications before deploying to production:  - Mainnet staging: `https://staging.api.tzkt.io/` or `https://staging.api.mainnet.tzkt.io/` ([view docs](https://staging.api.tzkt.io))  Feel free to contact us if you have any questions or feature requests. Your feedback really helps us make TzKT better!  - Discord: https://discord.gg/aG8XKuwsQd - Telegram: https://t.me/baking_bad_chat - Slack: https://tezos-dev.slack.com/archives/CV5NX7F2L - Twitter: https://twitter.com/TezosBakingBad - Email: hello@baking-bad.org  And don't forget to star TzKT project [on GitHub](https://github.com/baking-bad/tzkt) ;)  # Terms of Use  TzKT API is free for everyone and for both commercial and non-commercial usage.  If your application or service uses the TzKT API in any forms: directly on frontend or indirectly on backend, you should mention that fact on your website or application by placing the label **\"Powered by TzKT API\"** with a direct link to [tzkt.io](https://tzkt.io).   # Rate Limits  There will be no rate limits as long as our servers can handle the load without additional infrastructure costs. However, any apparent abuse will be prevented by setting targeted rate limits.  Check out [Tezos Explorer API Best Practices](https://baking-bad.org/blog/tag/TzKT/) and in particular [how to optimize requests count](https://baking-bad.org/blog/2020/07/29/tezos-explorer-api-tzkt-how-often-to-make-requests/).  --- 
 *
 * The version of the OpenAPI document: v1.7.0
 * Contact: hello@baking-bad.org
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.rarible.tezos.client.tzkt.apis

import java.io.IOException

import org.rarible.tezos.client.tzkt.models.Block
import org.rarible.tezos.client.tzkt.models.AccountParameter
import org.rarible.tezos.client.tzkt.models.DateTimeParameter
import org.rarible.tezos.client.tzkt.models.MichelineFormat
import org.rarible.tezos.client.tzkt.models.OffsetParameter
import org.rarible.tezos.client.tzkt.models.SelectParameter
import org.rarible.tezos.client.tzkt.models.SortParameter
import org.rarible.tezos.client.tzkt.models.Symbols

import org.rarible.tezos.client.tzkt.infrastructure.ApiClient
import org.rarible.tezos.client.tzkt.infrastructure.ApiResponse
import org.rarible.tezos.client.tzkt.infrastructure.ClientException
import org.rarible.tezos.client.tzkt.infrastructure.ClientError
import org.rarible.tezos.client.tzkt.infrastructure.ServerException
import org.rarible.tezos.client.tzkt.infrastructure.ServerError
import org.rarible.tezos.client.tzkt.infrastructure.MultiValueMap
import org.rarible.tezos.client.tzkt.infrastructure.RequestConfig
import org.rarible.tezos.client.tzkt.infrastructure.RequestMethod
import org.rarible.tezos.client.tzkt.infrastructure.ResponseType
import org.rarible.tezos.client.tzkt.infrastructure.Success

class BlocksApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "https://staging.api.tzkt.io")
        }
    }

    /**
    * Get blocks
    * Returns a list of blocks.
    * @param baker Filters blocks by baker. Allowed fields for &#x60;.eqx&#x60; mode: none. (optional)
    * @param level Filters blocks by level. (optional)
    * @param timestamp Filters blocks by timestamp. (optional)
    * @param priority Filters blocks by priority. (optional)
    * @param select Specify comma-separated list of fields to include into response or leave it undefined to return full object. If you select single field, response will be an array of values in both &#x60;.fields&#x60; and &#x60;.values&#x60; modes. (optional)
    * @param sort Sorts blocks by specified field. Supported fields: &#x60;id&#x60; (default), &#x60;level&#x60;, &#x60;priority&#x60;, &#x60;validations&#x60;, &#x60;reward&#x60;, &#x60;fees&#x60;. (optional)
    * @param offset Specifies which or how many items should be skipped (optional)
    * @param limit Maximum number of items to return (optional, default to 100)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return kotlin.collections.List<Block>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun blocksGet(baker: AccountParameter?, level: Int32Parameter?, timestamp: DateTimeParameter?, priority: Int32Parameter?, select: SelectParameter?, sort: SortParameter?, offset: OffsetParameter?, limit: kotlin.Int?, quote: Symbols?) : kotlin.collections.List<Block> {
        val localVarResponse = blocksGetWithHttpInfo(baker = baker, level = level, timestamp = timestamp, priority = priority, select = select, sort = sort, offset = offset, limit = limit, quote = quote)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.collections.List<Block>
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Get blocks
    * Returns a list of blocks.
    * @param baker Filters blocks by baker. Allowed fields for &#x60;.eqx&#x60; mode: none. (optional)
    * @param level Filters blocks by level. (optional)
    * @param timestamp Filters blocks by timestamp. (optional)
    * @param priority Filters blocks by priority. (optional)
    * @param select Specify comma-separated list of fields to include into response or leave it undefined to return full object. If you select single field, response will be an array of values in both &#x60;.fields&#x60; and &#x60;.values&#x60; modes. (optional)
    * @param sort Sorts blocks by specified field. Supported fields: &#x60;id&#x60; (default), &#x60;level&#x60;, &#x60;priority&#x60;, &#x60;validations&#x60;, &#x60;reward&#x60;, &#x60;fees&#x60;. (optional)
    * @param offset Specifies which or how many items should be skipped (optional)
    * @param limit Maximum number of items to return (optional, default to 100)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return ApiResponse<kotlin.collections.List<Block>?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun blocksGetWithHttpInfo(baker: AccountParameter?, level: Int32Parameter?, timestamp: DateTimeParameter?, priority: Int32Parameter?, select: SelectParameter?, sort: SortParameter?, offset: OffsetParameter?, limit: kotlin.Int?, quote: Symbols?) : ApiResponse<kotlin.collections.List<Block>?> {
        val localVariableConfig = blocksGetRequestConfig(baker = baker, level = level, timestamp = timestamp, priority = priority, select = select, sort = sort, offset = offset, limit = limit, quote = quote)

        return request<Unit, kotlin.collections.List<Block>>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation blocksGet
    *
    * @param baker Filters blocks by baker. Allowed fields for &#x60;.eqx&#x60; mode: none. (optional)
    * @param level Filters blocks by level. (optional)
    * @param timestamp Filters blocks by timestamp. (optional)
    * @param priority Filters blocks by priority. (optional)
    * @param select Specify comma-separated list of fields to include into response or leave it undefined to return full object. If you select single field, response will be an array of values in both &#x60;.fields&#x60; and &#x60;.values&#x60; modes. (optional)
    * @param sort Sorts blocks by specified field. Supported fields: &#x60;id&#x60; (default), &#x60;level&#x60;, &#x60;priority&#x60;, &#x60;validations&#x60;, &#x60;reward&#x60;, &#x60;fees&#x60;. (optional)
    * @param offset Specifies which or how many items should be skipped (optional)
    * @param limit Maximum number of items to return (optional, default to 100)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return RequestConfig
    */
    fun blocksGetRequestConfig(baker: AccountParameter?, level: Int32Parameter?, timestamp: DateTimeParameter?, priority: Int32Parameter?, select: SelectParameter?, sort: SortParameter?, offset: OffsetParameter?, limit: kotlin.Int?, quote: Symbols?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (baker != null) {
                    put("baker", listOf(baker.toString()))
                }
                if (level != null) {
                    put("level", listOf(level.toString()))
                }
                if (timestamp != null) {
                    put("timestamp", listOf(timestamp.toString()))
                }
                if (priority != null) {
                    put("priority", listOf(priority.toString()))
                }
                if (select != null) {
                    put("select", listOf(select.toString()))
                }
                if (sort != null) {
                    put("sort", listOf(sort.toString()))
                }
                if (offset != null) {
                    put("offset", listOf(offset.toString()))
                }
                if (limit != null) {
                    put("limit", listOf(limit.toString()))
                }
                if (quote != null) {
                    put("quote", listOf(quote.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v1/blocks",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * Get block by hash
    * Returns a block with the specified hash.
    * @param hash Block hash 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return Block
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun blocksGetByHash(hash: kotlin.String, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : Block {
        val localVarResponse = blocksGetByHashWithHttpInfo(hash = hash, operations = operations, micheline = micheline, quote = quote)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Block
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Get block by hash
    * Returns a block with the specified hash.
    * @param hash Block hash 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return ApiResponse<Block?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun blocksGetByHashWithHttpInfo(hash: kotlin.String, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : ApiResponse<Block?> {
        val localVariableConfig = blocksGetByHashRequestConfig(hash = hash, operations = operations, micheline = micheline, quote = quote)

        return request<Unit, Block>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation blocksGetByHash
    *
    * @param hash Block hash 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return RequestConfig
    */
    fun blocksGetByHashRequestConfig(hash: kotlin.String, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (operations != null) {
                    put("operations", listOf(operations.toString()))
                }
                if (micheline != null) {
                    put("micheline", listOf(micheline.toString()))
                }
                if (quote != null) {
                    put("quote", listOf(quote.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v1/blocks/{hash}".replace("{"+"hash"+"}", "$hash"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * Get block by level
    * Returns a block at the specified level.
    * @param level Block level 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return Block
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun blocksGetByLevel(level: kotlin.Int, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : Block {
        val localVarResponse = blocksGetByLevelWithHttpInfo(level = level, operations = operations, micheline = micheline, quote = quote)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Block
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Get block by level
    * Returns a block at the specified level.
    * @param level Block level 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return ApiResponse<Block?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun blocksGetByLevelWithHttpInfo(level: kotlin.Int, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : ApiResponse<Block?> {
        val localVariableConfig = blocksGetByLevelRequestConfig(level = level, operations = operations, micheline = micheline, quote = quote)

        return request<Unit, Block>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation blocksGetByLevel
    *
    * @param level Block level 
    * @param operations Flag indicating whether to include block operations into returned object or not (optional, default to false)
    * @param micheline Format of the parameters, storage and diffs: &#x60;0&#x60; - JSON, &#x60;1&#x60; - JSON string, &#x60;2&#x60; - raw micheline, &#x60;3&#x60; - raw micheline string (optional)
    * @param quote Comma-separated list of ticker symbols to inject historical prices into response (optional)
    * @return RequestConfig
    */
    fun blocksGetByLevelRequestConfig(level: kotlin.Int, operations: kotlin.Boolean?, micheline: MichelineFormat?, quote: Symbols?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (operations != null) {
                    put("operations", listOf(operations.toString()))
                }
                if (micheline != null) {
                    put("micheline", listOf(micheline.toString()))
                }
                if (quote != null) {
                    put("quote", listOf(quote.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v1/blocks/{level}".replace("{"+"level"+"}", "$level"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * Get blocks count
    * Returns the total number of blocks.
    * @return kotlin.Int
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun blocksGetCount() : kotlin.Int {
        val localVarResponse = blocksGetCountWithHttpInfo()

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.Int
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Get blocks count
    * Returns the total number of blocks.
    * @return ApiResponse<kotlin.Int?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun blocksGetCountWithHttpInfo() : ApiResponse<kotlin.Int?> {
        val localVariableConfig = blocksGetCountRequestConfig()

        return request<Unit, kotlin.Int>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation blocksGetCount
    *
    * @return RequestConfig
    */
    fun blocksGetCountRequestConfig() : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v1/blocks/count",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
