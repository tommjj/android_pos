package com.android.pos.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "products", indices = [Index(value = ["name"], unique = true)])
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryID: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val stock: Int,
)

data class ProductWithCategory(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "categoryID",
        entityColumn = "id"
    )
    val category: Category
)