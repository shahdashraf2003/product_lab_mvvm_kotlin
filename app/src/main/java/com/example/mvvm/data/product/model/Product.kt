package com.example.mvvm.data.product.model


import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.io.Serializable


data class ProductResponse(
    val products: List<Product>
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val thumbnail: String,
    val localPath: String? = null
): Serializable


