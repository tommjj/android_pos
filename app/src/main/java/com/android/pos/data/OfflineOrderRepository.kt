package com.android.pos.data

import kotlinx.coroutines.flow.Flow

class OfflineOrderRepository(private val orderDao: OrderDao): OrderRepository {
    override suspend fun insertOrder(order: Order): Long = orderDao.insertOrder(order)

    override suspend fun insertOrderProducts(vararg orderProducts: OrderProduct) = orderDao.insertOrderProducts(*orderProducts)

    override suspend fun insertOrder(order: Order, orderProducts: List<OrderProduct>): Long = orderDao.insertOrder(order, orderProducts)

    override fun getOrderStream(id: Int): Flow<Order> = orderDao.getOrder(id)

    override fun getOrderWithProductsStream(id: Int): Flow<OrderWithProducts> = orderDao.getOrderWithProducts(id)

    override fun getAllOrdersStream(): Flow<List<Order>> = orderDao.getAllOrders()

    override fun getAllOrdersWithProductsStream(): Flow<List<OrderWithProducts>> = orderDao.getAllOrdersWithProducts()

    override fun getOrdersCountStream(): Flow<Int> = orderDao.getOrdersCount()

    override suspend fun updateOrder(order: Order, orderProducts: List<OrderProduct>) = orderDao.updateOrder(order, orderProducts)

    override suspend fun updateOrder(order: Order) = orderDao.update(order)

    override suspend fun updateOrderProduct(orderProduct: OrderProduct) = orderDao.update(orderProduct)

    override suspend fun deleteOrder(order: Order) = orderDao.deleteOrder(order)
}