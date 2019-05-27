package com.oceanpremium.api.core.currentrms.response.dto.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oceanpremium.api.core.currentrms.response.dto.config.ConfigPropertyValue
import com.oceanpremium.api.core.currentrms.response.dto.config.ConfigProperty
import com.oceanpremium.api.core.exception.handler.ApiError
import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.exception.throwable.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import retrofit2.Response

class ProductConfigsDtoMapper(code: Int, response: Response<Any>?) : CurrentRmsBaseDtoMapper(code) {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        const val PRODUCT_CONFIG_OPTION_PREFIX = "custom_product_config_option"
    }

    init {
        /**
         * The root node of the response payload that will contain the response result for data key
         */
        data = mapToDto(response)
    }

    /**
     * Determine if response contains either a JSON object response or a JSON array response,
     * and parse accordingly.
     */
    @Throws(NotFoundException::class)
    private fun mapToDto(response: Response<Any>?): Any? {

        when {
            // Request failed, build error message
            response != null && !response.isSuccessful -> {
                val errorBody = response.errorBody()

                error = when {
                    errorBody != null -> {
                        val type = object : TypeToken<ErrorResponse>() {}.type
                        val errorResponse: ErrorResponse? = Gson().fromJson(response.errorBody()!!.charStream(), type)

                        ApiError(code = response.code(), message = errorResponse)
                    }

                    else -> {
                        ApiError(code = response.code(), message = response.message())
                    }
                }

                return null
            }

            // Request succeeded, parse response payload
            response != null && response.isSuccessful -> {
                val responseBody = response.body() as Map<*, *>

                data = when {
                    responseBody.containsKey("list_names") -> {

                        /**
                         * CurrentRms returns a 200 OK for empty result sets. As a best practice for REST API,
                         * the response should be a 404 NOT FOUND when an empty result set is returned.
                         *
                         * See @link: https://stackoverflow.com/questions/11746894/what-is-the-proper-rest-response-code-for-a-valid-request-but-an-empty-data
                         *
                         * Therefore, override the 200 OK response code and return a 404 NOT FOUND response code and error message instead.
                         */
                        val metaMapper = MetaDtoMapper(response)

                        if (metaMapper.overrideHttpStatus) {
                            httpStatus = HttpStatus.NOT_FOUND

                            val errorResponse = ErrorResponse()
                            errorResponse.errors.add(httpStatus.reasonPhrase)

                            error = ApiError(code = httpStatus.value(), message = errorResponse)

                            return null
                        }

                        meta = metaMapper.meta
                        mapJsonArray(response)
                    }

                    else -> null
                }
            }
        }

        return data
    }

    /**
     * Map list of items to list of dto's
     */
    private fun mapJsonArray(response: Response<Any>?): List<ConfigProperty>? {
        val responseBody = response?.body() as Map<*, *>
        val configurationItems: MutableList<ConfigProperty> = mutableListOf()

        @Suppress("UNCHECKED_CAST")
        val productsItemsBody = responseBody["list_names"] as List<Map<*, *>>

        productsItemsBody.forEach {

            val configProperty = mapJsonObjectToDto(it)

            if (configProperty != null) {
                configurationItems.add(configProperty)
            }
        }

        return configurationItems
    }

    /**
     * Map a single item to dto
     */
    @Throws(BadRequestException::class)
    private fun mapJsonObjectToDto(itemBody: Map<*, *>): ConfigProperty? {
        var id: Int? = null
        var name: String? = null
        val values: List<ConfigPropertyValue>? = mapConfigurationListValues(itemBody)

        var configProperty: ConfigProperty? = null

        try {

            if (itemBody.containsKey("id")) {
                id = (itemBody["id"] as Double?)?.toInt()
            }

            if (itemBody.containsKey("name")) {
                name = itemBody["name"] as String?

                when {
                    // Build a consumer friendly field name value for the concerning configuration
                    name != null && name.contains(PRODUCT_CONFIG_OPTION_PREFIX) -> {
                        logger.debug("Found config: $name")
                    }
                }
            }

            if (id != null && name != null && values != null) {
                configProperty = ConfigProperty(id, name, values)
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product configs to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return configProperty
    }

    private fun mapConfigurationListValues(itemBody: Map<*, *>): List<ConfigPropertyValue> {
        val items: MutableList<ConfigPropertyValue> = mutableListOf()

        try {

            if (itemBody["list_values"] != null) {
                @Suppress("UNCHECKED_CAST")
                val configValues: List<Map<*, *>> = itemBody["list_values"] as List<Map<*, *>>

                configValues.forEach {
                    var id: Int? = null
                    var name: String? = null

                    if (it.containsKey("id")) {
                        id = (it["id"] as Double?)?.toInt()
                    }

                    if (it.containsKey("name")) {
                        name = it["name"] as String?
                    }

                    if (id != null && name != null) {
                        items.add(
                            ConfigPropertyValue(
                                id,
                                name
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

            val message = "Failed to map product config item to Dto: ${e.message}"
            logger.error(message)

            throw BadRequestException(e.message)
        }

        return items
    }

}
