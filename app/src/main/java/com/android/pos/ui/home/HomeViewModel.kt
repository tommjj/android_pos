package com.android.pos.ui.home

import androidx.lifecycle.ViewModel
import com.android.pos.auth.Auth

class HomeViewModel(private val auth: Auth) :
    ViewModel() {
    fun getUserName(): String {
        return checkNotNull(auth.user?.name);
    }


}