package com.android.pos.data

import android.content.Context

interface AppContainer {
//    val itemsRepository: ItemsRepository
}
class AppDataContainer(private val context: Context) : AppContainer{
}