package com.example.mvvm.presentation.allproducts.view

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mvvm.data.product.model.Product
import com.example.mvvm.presentation.allproducts.viewmodel.ProductViewModel

@Composable
fun ProductItem(product: Product, viewModel: ProductViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(8.dp)

    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.description,
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
                .border(1.dp, Color.Black)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = product.title, fontSize = 20.sp)
            Button(
                onClick = {
                    viewModel.insetProduct(product)
                    Toast.makeText(context, "Added to Fav", Toast.LENGTH_SHORT).show()
                },
            ) {
                Text(text = "Add to Fav")
            }

        }
    }
}