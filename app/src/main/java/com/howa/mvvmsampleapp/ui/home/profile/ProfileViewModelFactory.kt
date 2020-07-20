package com.howa.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.howa.mvvmsampleapp.data.repositories.UserRepo

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val userRepo: UserRepo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepo) as T
    }
}