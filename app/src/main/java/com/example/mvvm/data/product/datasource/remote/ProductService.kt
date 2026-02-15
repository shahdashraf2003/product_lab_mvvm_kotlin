package com.example.mvvm.data.product.datasource.remote

import com.example.mvvm.data.product.model.ProductResponse
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getAllProducts(): ProductResponse
}