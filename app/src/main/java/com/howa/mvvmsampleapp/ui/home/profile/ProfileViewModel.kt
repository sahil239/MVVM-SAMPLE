package com.howa.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.howa.mvvmsampleapp.data.repositories.UserRepo

class ProfileViewModel(userRepo: UserRepo) : ViewModel() {

    val user = userRepo.getUser()

}