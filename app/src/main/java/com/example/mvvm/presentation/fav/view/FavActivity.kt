package com.example.mvvm.presentation.fav.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mvvm.data.product.model.Product
import com.example.mvvm.presentation.fav.viewModel.FavViewModel
import com.example.mvvm.presentation.fav.viewModel.FavViewModelFactory

class FavActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                FavProductScreen(
                    context = this
                )
            }
        }
    }

@Composable
fun FavProductScreen(
    context: Context,
) {
    val favViewModel: FavViewModel = viewModel(
        factory = FavViewModelFactory(context)
    )
    val products by favViewModel.favProducts.observeAsState(emptyList())
    val isLoading by favViewModel.isLoading.observeAsState(false)
    val error by favViewModel.errorMessage.observeAsState(null)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (products.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(products.size) { index ->
                    val product = products[index]
                    FavMovieItem(
                        product = product,
                        deleteFromFav = { favViewModel.removeFromFav(it) }
                    )
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        error?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FavMovieItem(product: Product, deleteFromFav: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(text = product.title )

                Text(text = "category: ${product.category}")
                Button(onClick = { deleteFromFav(product) }) {
                    Text(text = "Delete Favorite")
                }
            }
        }
    }
}
