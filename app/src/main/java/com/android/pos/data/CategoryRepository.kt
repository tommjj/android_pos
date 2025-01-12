package com.android.pos.data

import kotlinx.coroutines.flow.Flow


interface CategoryRepository {
    /**
     * [insertCategory] insert new category
     */
    suspend fun insertCategory(category: Category): Long

    /**
     * [getAllCategoriesStream] get all categories from database
     */
    fun getAllCategoriesStream(): Flow<List<Category>>

    /**
     * [getCategoryStream] get category by id
     */
    fun getCategoryStream(id: Int): Flow<Category>

    /**
     * [updateCategory] update category from the database
     */
    suspend fun updateCategory(category: Category)

    /**
     * [deleteCategory] delete category from the database
     */
    suspend fun deleteCategory(category: Category)
}