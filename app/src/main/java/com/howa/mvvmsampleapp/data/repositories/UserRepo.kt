package com.howa.mvvmsampleapp.data.repositories

import com.howa.mvvmsampleapp.data.db.AppDatabase
import com.howa.mvvmsampleapp.data.db.entities.User
import com.howa.mvvmsampleapp.data.network.APIService
import com.howa.mvvmsampleapp.data.network.SafeAPIRequest
import com.howa.mvvmsampleapp.data.network.responses.AuthResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class UserRepo(private val apiService: APIService, private val appDatabase: AppDatabase) :
    SafeAPIRequest() {

    private val application = "application/json"

    suspend fun userLogin(email: String, password: String): AuthResponse {

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        return apiRequest { apiService.userLogin(createPartFromJsonObject(jsonObject.toString())) }

    }

    suspend fun saveUser(user: User) = appDatabase.getUserDao().upsert(user)


    private fun createPartFromJsonObject(data: String?): RequestBody {
        return RequestBody.create(
            MediaType.parse(application),
            data
        )
    }

    fun getUser() = appDatabase.getUserDao().getUser()

}