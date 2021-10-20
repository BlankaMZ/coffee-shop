package com.example.coffeeshop.mapper

import com.example.coffeeshop.data.model.NetworkProduct
import com.example.coffeeshop.data.model.Product

class ProductListMapperRemote : BaseMapper<List<NetworkProduct>, List<Product>> {
    override fun transformToDomain(type: List<NetworkProduct>): List<Product> {
        return type.map { networkProduct ->
            Product(
                id = networkProduct.id,
                name = networkProduct.name,
                grossPrice = networkProduct.grossPrice
            )
        }
    }

    override fun transformToDto(type: List<Product>): List<NetworkProduct> {
        return type.map { product ->
            NetworkProduct(
                id = product.id,
                name = product.name,
                grossPrice = product.grossPrice
            )
        }
    }
}