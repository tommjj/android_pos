package com.android.pos.data

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProductsStream(): Flow<List<Product>>

    fun getProductStream(id: Int): Flow<Product>

    fun getProductStream(name: String): Flow<Product>

    fun getProductWithCategoryStream(id: Int): Flow<ProductWithCategory>

    fun getProductWithCategoryStream(name: String): Flow<ProductWithCategory>

    fun countProducts(): Flow<Int>

    suspend fun insertProduct(product: Product): Long

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}