package com.android.pos

import android.app.Application
import com.android.pos.auth.Auth
import com.android.pos.data.AppContainer
import com.android.pos.data.IAppContainer

class PosApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: IAppContainer
    lateinit var auth: Auth

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        auth = Auth(container)
    }
}