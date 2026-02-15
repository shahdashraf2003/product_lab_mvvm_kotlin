package com.example.mvvm.presentation.allproducts.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.product.model.Product
import com.example.mvvm.data.product.repository.ProductRepo
import kotlinx.coroutines.launch

class ProductViewModel(context: Context) : ViewModel(){


    private val productRepo : ProductRepo = ProductRepo(
        context
    )
    //mutable to change the value
    //live data can't change and abstract can't create obj
    //live data can be observed
    //private val _products = MutableLiveData<List<Product>>(emptyList())
    private val _products: MutableState<List<Product>> = mutableStateOf(emptyList())
    val products : State<List<Product>> = _products
    private val _errorMessage = mutableStateOf<String>("")
    val errorMessage : State<String> = _errorMessage

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading : State<Boolean> = _isLoading
    init {
        getAllProducts()
    }



    private fun getAllProducts() {
        _isLoading.value = true
        viewModelScope.launch{
            try {
                val response = productRepo.getAllProducts()
                _products.value = response.products
            } catch (e: Exception) {
                _errorMessage.value = e.message.toString()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun insetProduct(product: Product) {
        viewModelScope.launch{
            try {
                productRepo.insertProduct(product)

            } catch (e: Exception) {
                Log.e("TAG", "insetProduct: +${e.message}", )
            }
        }
    }


}
class ProductViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductViewModel(context) as T
    }
}