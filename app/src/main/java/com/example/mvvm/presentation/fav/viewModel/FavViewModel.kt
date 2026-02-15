package com.example.mvvm.presentation.fav.viewModel

import ProductLocalDataSource
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.product.model.Product
import com.example.mvvm.data.product.repository.ProductRepo
import kotlinx.coroutines.launch

class FavViewModel(context: Context) : ViewModel() {
    private val productRepo : ProductRepo = ProductRepo(
        context
    )

    private val _favProducts = MutableLiveData<List<Product>>(emptyList())
    val favProducts: LiveData<List<Product>> = _favProducts

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadFavProducts()
    }

    fun loadFavProducts() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _favProducts.value = productRepo.getAllFavProducts()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e("FavViewModel", "loadFavProducts: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun removeFromFav(product: Product) {
        viewModelScope.launch {
            try {
                productRepo.deleteProduct(product)
                loadFavProducts()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e("FavViewModel", "removeFromFav: ${e.message}")
            }
        }
    }
}


class FavViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
