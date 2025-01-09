package com.android.pos.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.pos.PosApplication
import com.android.pos.ui.home.HomeViewModel
import com.android.pos.ui.login.LoginViewModel
import com.android.pos.ui.signup.SignUpViewModel

/**
 * AppViewModelProvider Factory to create instance of ViewModel for the entire app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // initialise LoginViewModel
        initializer {
            LoginViewModel(
                posApplication().auth,
                posApplication().getSharedPreferences("login", Application.MODE_PRIVATE)
            )
        }

        // initialise SignUpViewModel
        initializer {
            SignUpViewModel(posApplication().container.userRepository)
        }

        // initialise HomeViewModel
        initializer {
            HomeViewModel(posApplication().auth)
        }
    }
}


/**
 * Extension function to queries for [Application] object and returns an instance of
 * [PosApplication].
 */
fun CreationExtras.posApplication(): PosApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PosApplication)