package com.android.pos.data

import kotlinx.coroutines.flow.Flow

class OfflineProductRepository(private val productDao: ProductDao) : ProductRepository {
    override fun getAllProductsStream(): Flow<List<Product>> = productDao.getAllProducts()

    override fun getProductStream(id: Int): Flow<Product> = productDao.getProduct(id)

    override fun getProductStream(name: String): Flow<Product> = productDao.getProduct(name)

    override fun getProductWithCategoryStream(id: Int): Flow<ProductWithCategory> =
        productDao.getProductWithCategory(id)

    override fun getProductWithCategoryStream(name: String): Flow<ProductWithCategory> =
        productDao.getProductWithCategory(name)

    override fun countProducts(): Flow<Int> = productDao.getProductsCount()

    override suspend fun insertProduct(product: Product) = productDao.insert(product)

    override suspend fun updateProduct(product: Product) = productDao.update(product)

    override suspend fun deleteProduct(product: Product) = productDao.delete(product)
}