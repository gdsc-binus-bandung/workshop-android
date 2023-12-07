package com.example.retrofit

import com.example.retrofit.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //value untuk request dan response
    private val interceptor: HttpLoggingInterceptor=HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //value untuk akses HTTP
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    //value untuk komunikasi dengan server
    val api: ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiService.BASE_URL)
        .client(client)
        .build()
        .create(ApiService::class.java)
}

