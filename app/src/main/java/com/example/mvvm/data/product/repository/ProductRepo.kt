package com.example.mvvm.data.product.repository

import ProductLocalDataSource
import android.content.Context
import com.example.mvvm.data.product.datasource.remote.ProductRemoteDataSource
import com.example.mvvm.data.product.model.Product

class ProductRepo(context: Context) {
    private val productRemoteDataSource = ProductRemoteDataSource()
    private val productLocalDataSource = ProductLocalDataSource(context)


    suspend fun getAllProducts() = productRemoteDataSource.getAllProducts()
    suspend fun insertProduct(product: Product) = productLocalDataSource.insertProduct(product)
    suspend fun deleteProduct(product: Product) = productLocalDataSource.deleteProduct(product)
    suspend fun getAllFavProducts() = productLocalDataSource.getAllProducts()





}