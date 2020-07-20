package com.howa.mvvmsampleapp.data.network.responses

import com.howa.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val success: Boolean?,
    val message: String?,
    val status_code: Int?,
    val data: User?
)