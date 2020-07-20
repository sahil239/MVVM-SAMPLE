package com.howa.mvvmsampleapp.ui.auth

import com.howa.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(response: User?)
    fun onFailure(message: String)
}