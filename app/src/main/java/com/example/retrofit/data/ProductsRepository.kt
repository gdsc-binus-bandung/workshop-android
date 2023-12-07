package com.example.retrofit.data

import com.example.retrofit.data.model.Product

import kotlinx.coroutines.flow.Flow

//Request data to API
interface ProductsRepository {
    suspend fun getProductsList(): Flow<Result<List<Product>>>
}