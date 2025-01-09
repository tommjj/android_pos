package com.android.pos.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productID: Int,
    val totalPrice: Double,
    @ColumnInfo(defaultValue =  "('Created at' || CURRENT_TIMESTAMP)")
    val orderDate: Date,
    @ColumnInfo(defaultValue =  "('Created at' || CURRENT_TIMESTAMP)")
    val deliveryDate: Date,
)

@Entity(tableName = "order_products", primaryKeys = ["orderID", "productID"])
data class OrderProduct(
    val orderID: Int,
    val productID: Int,
    val quantity: Int,
    val price: Double,
)

data class OrderWithProducts(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "productID",
    )
    val products: List<OrderProduct>
)

