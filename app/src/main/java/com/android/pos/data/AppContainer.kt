package com.android.pos.data

import android.content.Context

interface IAppContainer {
    val userRepository: UserRepository

    var dataContainer: IDataContainer?

    /**
     * [loadDataContainer] create new data container by user id
     */
    fun loadDataContainer(id : Int)
}

class AppContainer(private val context: Context) : IAppContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(MasterDatabase.getDatabase(context).userDao())
    }

    override var dataContainer: IDataContainer? = null

    override fun loadDataContainer(id: Int) {
        TODO("Not yet implemented")
    }
}