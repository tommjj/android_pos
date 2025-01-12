package com.android.pos.data

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun insertCategory(category: Category) = categoryDao.insert(category)

    override fun getAllCategoriesStream() = categoryDao.getAllCategories()

    override fun getCategoryStream(id: Int) = categoryDao.getCategory(id)

    override suspend fun updateCategory(category: Category) = categoryDao.update(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.delete(category)
}
