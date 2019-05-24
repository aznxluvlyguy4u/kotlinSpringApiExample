package com.oceanpremium.api.core.currentrms.response.dto.config

interface ProductConfigOptionsResolver {
    fun resolveForProduct(id: Int): List<ConfigProperty>
}

class ProductConfigOptionsResolverImpl(private val dtos: List<ConfigProperty>) : ProductConfigOptionsResolver {

    override fun resolveForProduct(id: Int): List<ConfigProperty> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}

