package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.ProductsRepository
import com.example.retrofit.data.Result
import com.example.retrofit.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsRepository: ProductsRepository):ViewModel() {
    //initiate the storage to get the list of data from API
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    //initiate the productsRepository Response
    init {
        viewModelScope.launch {
            productsRepository.getProductsList().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let{products ->
                            _products.update{products}
                        }
                    }
                }
            }
        }
    }
}