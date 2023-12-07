package com.example.retrofit.data

import com.example.retrofit.data.model.Products
import retrofit2.http.GET

interface ApiService {
    //Request data from API
    @GET("products")
    suspend fun getProductsList(): Products

    //initiate the URL
    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}



//@GET("products/type")
//suspend fun getProductsList(
//    @Path("type") type:String,
//    @Query("page") page: Int,
//    @Query("api_key") apiKey: String
//): Products