package com.howa.mvvmsampleapp.data.network

import com.google.gson.GsonBuilder
import com.howa.mvvmsampleapp.data.network.responses.AuthResponse
import com.howa.mvvmsampleapp.data.network.responses.ProductsResponse
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {

    @POST("user/login")
    suspend fun userLogin(@Body requestBody: RequestBody/*, @Field("email") email : String, @Field("password") password : String*/): Response<AuthResponse>


    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    companion object {
        operator fun invoke(networkInterceptor: NetworkInterceptor): APIService {

            val httpClient = OkHttpClient.Builder()

            //Create a new Interceptor.
            val headerAuthorizationInterceptor = Interceptor { chain ->
                var request = chain.request()
                val headers: Headers = request.headers().newBuilder()
                    .add("Authorization", "Bearer howa@inventory").build()
                request = request.newBuilder().headers(headers).build()
                chain.proceed(request)
            }

            val gson = GsonBuilder()
                .setLenient()
                .create()

            httpClient.connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(headerAuthorizationInterceptor).addInterceptor(networkInterceptor)

            return Retrofit.Builder().baseUrl("http://110.78.164.7:8090/api/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
                .create(APIService::class.java)
        }
    }
}