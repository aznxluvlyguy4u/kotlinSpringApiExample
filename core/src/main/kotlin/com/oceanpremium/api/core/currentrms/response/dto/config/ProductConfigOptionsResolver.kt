package com.oceanpremium.api.core.currentrms.response.dto.config

import com.oceanpremium.api.core.currentrms.response.dto.mapper.ProductConfigsDtoMapper.Companion.PRODUCT_CONFIG_OPTION_PREFIX
import com.oceanpremium.api.core.currentrms.response.dto.product.ProductDto
import org.slf4j.LoggerFactory

class ProductConfigOptionsResolverImpl(allConfigIds: List<ConfigProperty>?, product: ProductDto?) {

    var data: List<ConfigProperty>? = null

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    init {
        logger.debug("all product config ids: $allConfigIds")
        logger.debug("product config ids: ${product?.rawConfigurationIds}")

        data = resolveConfigOptionIdsToConfigOptionNames(allConfigIds, product)
    }

    private fun resolveConfigOptionIdsToConfigOptionNames(
        allConfigIds: List<ConfigProperty>?,
        product: ProductDto?
    ): List<ConfigProperty>? {
        val resolvedConfigProperties: MutableList<ConfigProperty> = mutableListOf()

        product?.rawConfigurationIds?.forEach { configField ->
            //Matching config property to hold values
            val resolvedConfigProperty = ConfigProperty()

            logger.debug("resolve product config property: ${configField.fieldName}")

            //Containing list of matching options
            val configPropertyValues: MutableList<ConfigPropertyValue> = mutableListOf()

            configField.ids?.forEach { rawConfigId ->
                val rawConfigProperty = allConfigIds?.firstOrNull { configId -> configId.name == configField.fieldName }

                logger.debug("resolve: ${rawConfigProperty?.name} for id: $rawConfigId")

                val matchingConfigPropertyValue = rawConfigProperty?.values?.firstOrNull { it.id == rawConfigId }
                if (matchingConfigPropertyValue != null) {

                    logger.debug("Found Matching config property values : ${matchingConfigPropertyValue.id} - ${matchingConfigPropertyValue.name}")

                    configPropertyValues.add(matchingConfigPropertyValue)

                    resolvedConfigProperty.id = rawConfigProperty.id

                    // Build a consumer friendly field name value
                    var consumerFriendlyConfigName = rawConfigProperty.name
                    consumerFriendlyConfigName = consumerFriendlyConfigName?.replace(PRODUCT_CONFIG_OPTION_PREFIX + "_", "")
                    consumerFriendlyConfigName = consumerFriendlyConfigName?.capitalize()

                    resolvedConfigProperty.name = consumerFriendlyConfigName
                }
            }

            resolvedConfigProperty.values = configPropertyValues
            resolvedConfigProperties.add(resolvedConfigProperty)

        }

        return resolvedConfigProperties
    }

}

