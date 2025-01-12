package com.android.pos.ui.profile

import androidx.lifecycle.ViewModel
import com.android.pos.auth.Auth

class ProfileHomeViewModel(
    private val auth: Auth,
) : ViewModel() {
    fun getUserName(): String {
        return checkNotNull(auth.user?.name)
    }

    fun logout() {
        auth.logout()
    }
}