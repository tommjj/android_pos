package com.android.pos.data

import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    /**
     * [insertOrder] insert new order
     */
    suspend fun insertOrder(order: Order): Long

    /**
     * [insertOrderProducts] insert new order products
     */
    suspend fun insertOrderProducts(vararg orderProducts: OrderProduct)

    /**
     * [insertOrder] insert new order with products
     */
    suspend fun insertOrder(order: Order, orderProducts: List<OrderProduct>): Long

    /**
     * [getOrderStream] get order by id
     */
    fun getOrderStream(id: Int): Flow<Order>

    /**
     * [getOrderWithProductsStream] get order with products by id
     */
    fun getOrderWithProductsStream(id: Int): Flow<OrderWithProducts>

    /**
     * [getAllOrdersStream] get all orders
     */
    fun getAllOrdersStream(): Flow<List<Order>>

    /**
     * [getAllOrdersWithProductsStream] get all orders with products
     */
    fun getAllOrdersWithProductsStream(): Flow<List<OrderWithProducts>>

    /**
     * [getOrdersCountStream] get orders count
     */
    fun getOrdersCountStream(): Flow<Int>

    /**
     * [updateOrder] update order
     */
    suspend fun updateOrder(order: Order, orderProducts: List<OrderProduct>)

    /**
     * [updateOrder] update order
     */
    suspend fun updateOrder(order: Order)

    /**
     * [updateOrderProduct] update order
     */
    suspend fun updateOrderProduct(orderProduct: OrderProduct)

    /**
     * [deleteOrder] delete order
     */
    suspend fun deleteOrder(order: Order)
}