package com.android.pos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product): Long

    @Query("SELECT * from products WHERE id = :id")
    fun getProduct(id: Int): Flow<Product>

    @Query("SELECT * from products WHERE name = :name")
    fun getProduct(name: String): Flow<Product>

    @Transaction
    @Query("SELECT * from products WHERE id = :id")
    fun getProductWithCategory(id: Int): Flow<ProductWithCategory>

    @Transaction
    @Query("SELECT * from products WHERE name = :name")
    fun getProductWithCategory(name: String): Flow<ProductWithCategory>

    @Query("SELECT * from products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT COUNT(*) from products")
    fun getProductsCount(): Flow<Int>

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}