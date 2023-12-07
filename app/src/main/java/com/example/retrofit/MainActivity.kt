package com.example.retrofit

import android.os.Bundle
import android.view.RoundedCorner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.retrofit.data.ProductsRepository
import com.example.retrofit.data.ProductsRepositoryImpl
import com.example.retrofit.data.model.Product
import com.example.retrofit.presentation.ProductsViewModel
import com.example.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    //Initate the ViewModel and get the response from the ViewModel
    private val viewModel by viewModels<ProductsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Get the data from ViewModel
                    val productList = viewModel.products.collectAsState().value
                    val context = LocalContext.current
                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if (show) {
                                Toast.makeText(
                                    context, "Error", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    if (productList.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(productList.size) { index ->
                                Product(productList[index])
                            }
                        }
                    }
                }
            }
        }
    }

    //Design the product UI
    @Composable
    fun Product(product: Product) {
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(product.thumbnail)
                .size(Size.ORIGINAL).build()
        ).state
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(300.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            if (imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    painter = imageState.painter, contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${product.title} -- Price: ${product.price}$",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = product.description,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 13.sp,
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RetrofitTheme {
//        Greeting("Android")
//    }
//}