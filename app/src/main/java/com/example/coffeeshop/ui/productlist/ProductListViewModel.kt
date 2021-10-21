package com.example.coffeeshop.ui.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.data.model.Product
import com.example.coffeeshop.data.source.repository.CoffeeShopRepository
import com.example.coffeeshop.utils.Result as thisResult
import com.example.coffeeshop.utils.asLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val repository: CoffeeShopRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>?>()
    val products = _products.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    private var searchJob: Job? = null

    fun searchDebounced(searchText: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            refreshProducts(searchText)
        }
    }

    fun refreshProducts(name: String?) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getProducts(true, name)) {
                is thisResult.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        val products = result.data
                        _products.postValue(products)
                        _dataFetchState.postValue(true)

                    } else {
                        _dataFetchState.postValue(false)
                        _products.postValue(null)
                    }
                }

                is thisResult.Error -> {
                    _dataFetchState.value = false
                    _isLoading.value = false
                }

                is thisResult.Loading -> _isLoading.postValue(true)
            }
        }
    }
}