package com.example.mvvm.data.product.datasource.remote

import android.util.Log
import com.example.mvvm.data.product.model.ProductResponse
import com.example.mvvm.data.product.datasource.remote.ProductService
import com.example.mvvm.data.network.RetrofitHelper


class ProductRemoteDataSource(
    private val productService: ProductService = RetrofitHelper.retrofitService
) {

    suspend fun getAllProducts(): ProductResponse {
        val response = productService.getAllProducts()
        Log.e("TAG", "getAllProducts: +${response.products}", )
        return response
    }
}
