package com.howa.mvvmsampleapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.howa.mvvmsampleapp.data.repositories.UserRepo

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val userRepo: UserRepo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepo) as T
    }
}