package com.example.retrofit.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.retrofit.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

//A class for request the data and receive the respont from the API Service
class ProductsRepositoryImpl(private val api: ApiService): ProductsRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getProductsList(): Flow<Result<List<Product>>> {
        return flow{
            val productsFromApi = try{
                api.getProductsList()
            }catch (e:IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Products"))
                return@flow
            }catch (e:HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading"))
                return@flow
            }catch (e:Exception){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading"))
                return@flow
            }
            emit(Result.Success(productsFromApi.products))
        }
    }
}