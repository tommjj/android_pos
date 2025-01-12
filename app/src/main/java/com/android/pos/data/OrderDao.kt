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
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderProducts(vararg orderProduct: OrderProduct)

    @Transaction
    suspend fun insertOrder(order: Order, orderProducts: List<OrderProduct>): Long {
        val orderID = insertOrder(order)
        insertOrderProducts(*orderProducts.map { it.copy(orderID = orderID.toInt()) }
            .toTypedArray())
        return orderID
    }

    @Query("SELECT * from orders WHERE id = :id")
    fun getOrder(id: Int): Flow<Order>

    @Transaction
    @Query("SELECT * from orders WHERE id = :id")
    fun getOrderWithProducts(id: Int): Flow<OrderWithProducts>

    @Query("SELECT * from orders ORDER BY id ASC")
    fun getAllOrders(): Flow<List<Order>>

    @Transaction
    @Query("SELECT * from orders ORDER BY id ASC")
    fun getAllOrdersWithProducts(): Flow<List<OrderWithProducts>>

    @Query("SELECT COUNT(*) from orders")
    fun getOrdersCount(): Flow<Int>

    @Update
    suspend fun update(order: Order)

    @Update
    suspend fun update(orderProduct: OrderProduct)

    @Transaction
    suspend fun updateOrder(order: Order, orderProducts: List<OrderProduct>) {
        update(order)
        deleteOrderProducts(order.id)
        insertOrderProducts(*orderProducts.map { it.copy(orderID = order.id) }.toTypedArray())
    }

    @Delete
    suspend fun delete(order: Order)

    @Delete
    suspend fun deleteOrderProducts(vararg orderProducts: OrderProduct)

    @Transaction
    suspend fun deleteOrder(order: Order) {
        delete(order)
        deleteOrderProducts(order.id)
    }

    @Query("DELETE FROM order_products WHERE orderID = :orderID")
    suspend fun deleteOrderProducts(orderID: Int)
}