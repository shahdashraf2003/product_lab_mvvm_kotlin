package com.example.mvvm.data.network

import com.example.mvvm.data.product.datasource.remote.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton object
object RetrofitHelper {
    private val retrofit = Retrofit.Builder()
        //convert JSON object to  Kotlin object
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dummyjson.com/")
        .build()

    val retrofitService : ProductService =
        retrofit.create(ProductService::class.java)
}