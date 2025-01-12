package com.android.pos.data

import android.content.Context

/**
 * [IDataContainer] data container contain all data repository
 */
interface IDataContainer {
    val categoryRepository: CategoryRepository

    val productRepository: ProductRepository
}

class DataContainer(private val context: Context, private val id: Int) : IDataContainer {
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(
            POSDatabase.getDatabase(
                ctx = context,
                name = id.toString()
            ).categoryDao()
        )
    }

    override val productRepository: ProductRepository by lazy {
        OfflineProductRepository(
            POSDatabase.getDatabase(
                ctx = context,
                name = id.toString()
            ).productDao()
        )
    }
}
