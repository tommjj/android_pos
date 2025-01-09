package com.android.pos.data

import android.content.Context

interface IAppContainer {
    val userRepository: UserRepository

    var dataContainer: IDataContainer?

    /**
     * [initDataContainer] create new data container by username
     */
    fun initDataContainer(id: Int)
}

class AppContainer(private val context: Context) : IAppContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(MasterDatabase.getDatabase(context).userDao())
    }

    override var dataContainer: IDataContainer? = null

    override fun initDataContainer(id: Int) {
        val db = POSDatabase.getDatabase(context, id.toString())
    }
}