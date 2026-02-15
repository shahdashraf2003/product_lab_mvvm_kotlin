package com.example.mvvm.presentation.allproducts.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mvvm.data.product.model.Product
import com.example.mvvm.presentation.allproducts.viewmodel.ProductViewModel
import com.example.mvvm.presentation.allproducts.viewmodel.ProductViewModelFactory
import com.example.mvvm.presentation.fav.view.FavActivity

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

              Products()

        }
    }
}



@Composable
fun Products(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
) {
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(context)
    )

    val products by viewModel.products
    val errorMessage by viewModel.errorMessage
    val isLoading by viewModel.isLoading

    when {
        isLoading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        errorMessage.isNotEmpty() -> Text("Error: $errorMessage")
        else -> LazyColumn(modifier = modifier) {
           
            item {
                Button(onClick = {
                    val intent = Intent(context, FavActivity::class.java)
                    context.startActivity(intent)

                },
                    modifier = Modifier.padding(32.dp)
                    
                ) {
                    Text(text = "fav screen")
                }
            }
            items(products.size) { i ->
                ProductItem(
                    product = products[i],
                    viewModel=viewModel


                )
            }
        }
    }
}

