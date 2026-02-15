package com.example.mvvm.data.product.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.data.product.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)


}