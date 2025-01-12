package com.android.pos.data

import android.content.Context

interface IAppContainer {
    val userRepository: UserRepository

    var dataContainer: IDataContainer?

    /**
     * [initDataContainer] create new data container by id
     *
     * id = 0 means no data container
     */
    fun initDataContainer(id: Int)
}

class AppContainer(private val context: Context) : IAppContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(MasterDatabase.getDatabase(context).userDao())
    }

    override var dataContainer: IDataContainer? = null

    override fun initDataContainer(id: Int) {
        if (id == 0) {
            dataContainer = null
            return
        }
        dataContainer = DataContainer(context, id)
    }
}