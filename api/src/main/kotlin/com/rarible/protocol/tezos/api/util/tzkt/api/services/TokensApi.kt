/**
* TzKT API
* # Introduction  TzKT Explorer provides free REST API and WebSocket API for accessing detailed Tezos blockchain data and helps developers build more services and applications on top of Tezos. TzKT is an open-source project, so you can easily clone and build it and use it as a self-hosted service to avoid any risks of depending on third-party services.  TzKT API is available for the following Tezos networks with the following base URLs:  - Mainnet: `https://api.tzkt.io/` or `https://api.mainnet.tzkt.io/` ([view docs](https://api.tzkt.io))  - Granadanet: `https://api.granadanet.tzkt.io/` ([view docs](https://api.granadanet.tzkt.io))     - Hangzhou2net: `https://api.hangzhou2net.tzkt.io/` ([view docs](https://api.hangzhou2net.tzkt.io))  We also provide a staging environment for testing newest features and pre-updating client applications before deploying to production:  - Mainnet staging: `https://staging.api.tzkt.io/` or `https://staging.api.mainnet.tzkt.io/` ([view docs](https://staging.api.tzkt.io))  Feel free to contact us if you have any questions or feature requests. Your feedback really helps us make TzKT better!  - Discord: https://discord.gg/aG8XKuwsQd - Telegram: https://t.me/baking_bad_chat - Slack: https://tezos-dev.slack.com/archives/CV5NX7F2L - Twitter: https://twitter.com/TezosBakingBad - Email: hello@baking-bad.org  And don't forget to star TzKT project [on GitHub](https://github.com/baking-bad/tzkt) ;)  # Terms of Use  TzKT API is free for everyone and for both commercial and non-commercial usage.  If your application or service uses the TzKT API in any forms: directly on frontend or indirectly on backend, you must mention that fact on your website or application by placing the label **\"Powered by TzKT API\"** or **\"Built with TzKT API\"** with a direct link to [tzkt.io](https://tzkt.io).   # Rate Limits  There will be no rate limits as long as our servers can handle the load without additional infrastructure costs. However, any apparent abuse will be prevented by setting targeted rate limits.  Check out [Tezos Explorer API Best Practices](https://baking-bad.org/blog/tag/TzKT/) and in particular [how to optimize requests count](https://baking-bad.org/blog/2020/07/29/tezos-explorer-api-tzkt-how-often-to-make-requests/).  --- 
*
* The version of the OpenAPI document: v1.7.0
* Contact: hello@baking-bad.org
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.rarible.protocol.tezos.api.util.tzkt.api.services

import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ApiClient
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ClientException
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ClientError
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ServerException
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ServerError
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.RequestConfig
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.RequestMethod
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.ResponseType
import com.rarible.protocol.tezos.api.util.tzkt.api.infrastructure.Success
import com.rarible.protocol.tezos.api.util.tzkt.api.models.*

class TokensApi(basePath: String = defaultBasePath) : ApiClient(basePath) {
    companion object {


        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty("org.openapitools.client.baseUrl", "https://api.tzkt.io")
        }
    }

    /**
    * Get token transfers
    * Returns a list of token transfers.
    * @param tokenPeriodContract Filter by contract address.   Click on the parameter to expand more details. (optional)
    * @param tokenPeriodTokenId Filter by tokenId (for FA1.2 tokens tokenId is always &#x60;\&quot;0\&quot;&#x60;).   Click on the parameter to expand more details. (optional)
    * @param sort Sorts items (asc or desc) by the specified field. You can see what fileds can be used for sorting in the response description, below.   Click on the parameter to expand more details. (optional)
    * @param offset Specifies which or how many items should be skipped.   Click on the parameter to expand more details. (optional)
    * @param limit Maximum number of items to return. (optional)
    * @return kotlin.Array<TokenTransfer>
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun tokensGetTokenTransfers(tokenPeriodContract: String?, tokenPeriodTokenId: String?, sort: SortParameter?, offset: OffsetParameter?, limit: Int?) : Array<TokenTransfer> {
        val localVariableBody: Any? = null
        val localVariableQuery = mutableMapOf<String, List<String>>()
            .apply {
                if (tokenPeriodContract != null) {
                    put("token.contract", listOf(tokenPeriodContract.toString()))
                }
                if (tokenPeriodTokenId != null) {
                    put("token.tokenId", listOf(tokenPeriodTokenId.toString()))
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
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/v1/tokens/transfers",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val localVarResponse = request<Array<TokenTransfer>>(
            localVariableConfig,
            localVariableBody
        )

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Array<TokenTransfer>
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
    * Get tokens
    * Returns a list of tokens.
    * @param contract Filter by contract address.   Click on the parameter to expand more details. (optional)
    * @param tokenId Filter by tokenId (for FA1.2 tokens tokenId is always &#x60;\&quot;0\&quot;&#x60;).   Click on the parameter to expand more details. (optional)
    * @param sort Sorts items (asc or desc) by the specified field. You can see what fileds can be used for sorting in the response description, below.   Click on the parameter to expand more details. (optional)
    * @param offset Specifies which or how many items should be skipped.   Click on the parameter to expand more details. (optional)
    * @param limit Maximum number of items to return. (optional)
    * @return kotlin.Array<Token>
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun tokensGetTokens(contract: String?, tokenId: String?, sort: SortParameter?, offset: OffsetParameter?, limit: Int?) : Array<Token> {
        println("tokensGetTokens0")
        val localVariableBody: Any? = null
        val localVariableQuery = mutableMapOf<String, List<String>>()
            .apply {
                if (contract != null) {
                    put("contract", listOf(contract.toString()))
                }
                if (tokenId != null) {
                    put("tokenId", listOf(tokenId.toString()))
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
            }
        println("tokensGetTokens1")
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/v1/tokens",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        println("tokensGetTokens2")
        val localVarResponse = request<Array<Token>>(
            localVariableConfig,
            localVariableBody
        )
        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Array<Token>
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
     * Get token balances
     * Returns a list of token balances.
     * @param tokenPeriodContract Filter by contract address.   Click on the parameter to expand more details. (optional)
     * @param tokenPeriodTokenId Filter by tokenId (for FA1.2 tokens tokenId is always &#x60;\&quot;0\&quot;&#x60;).   Click on the parameter to expand more details. (optional)
     * @return kotlin.Array<TokenBalance>
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun tokensGetTokenBalances(tokenPeriodContract: String?, tokenPeriodTokenId: String?) : Array<TokenBalance> {
        val localVariableBody: Any? = null
        val localVariableQuery = mutableMapOf<String, List<String>>()
            .apply {
                if (tokenPeriodContract != null) {
                    put("token.contract", listOf(tokenPeriodContract.toString()))
                }
                if (tokenPeriodTokenId != null) {
                    put("token.tokenId", listOf(tokenPeriodTokenId.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/v1/tokens/balances",
            query = localVariableQuery,
            headers = localVariableHeaders
        )
        val localVarResponse = request<Array<TokenBalance>>(
            localVariableConfig,
            localVariableBody
        )

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Array<TokenBalance>
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

}
