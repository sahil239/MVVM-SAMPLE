package com.howa.mvvmsampleapp.data.network

import android.util.Log
import com.howa.mvvmsampleapp.util.APIExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeAPIRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()

        if (response.isSuccessful) {
            Log.d("TAG>>", "")
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    message.append("\n")
                }

            }

            message.append("Error Code : ${response.code()}")
            throw APIExceptions(message.toString())
        }
    }
}