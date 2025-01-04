package com.android.pos.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "categories", indices = [Index(value = ["name"], unique = true)])
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
)

data class CategoryWithProducts(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryID"
    )
    val products: List<Product>
)