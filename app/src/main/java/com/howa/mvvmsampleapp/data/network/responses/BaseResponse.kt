package com.howa.mvvmsampleapp.data.network.responses

data class BaseResponse(
    val success: Boolean,
    val status_code: Int,
    val message: String,
    val data: Unit
)