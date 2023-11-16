package com.bala.decathlon.fragments.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bala.decathlon.fragments.main.data.DecathlonProduct
import com.bala.decathlon.fragments.main.data.DecathlonProductRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val sortByName
        get() = savedStateHandle.getLiveData(KEY_SORT_BY_NAME, false)

    val sortByPrice
        get() = savedStateHandle.getLiveData(KEY_SORT_BY_PRICE, false)

    val queryFilter
        get() = savedStateHandle.getLiveData(KEY_FILTER,"").asFlow()

    @ExperimentalPagingApi
    fun getDecathlonProducts(sortByName: Boolean, sortByPrice: Boolean, queryFilter: String): Flow<PagingData<DecathlonProduct>> {
        return DecathlonProductRepository.getInstance().letDecathlonProductsDb(sortByName = sortByName, sortByPrice = sortByPrice, queryFilter = queryFilter).cachedIn(viewModelScope)
    }

    fun setSortByName(sortByName: Boolean) {
        savedStateHandle.set(KEY_SORT_BY_NAME, sortByName)
        savedStateHandle.set(KEY_SORT_BY_PRICE, !sortByName)
    }

    fun setSortByPrice(sortByPrice: Boolean) {
        savedStateHandle.set(KEY_SORT_BY_NAME, !sortByPrice)
        savedStateHandle.set(KEY_SORT_BY_PRICE, sortByPrice)
    }

    fun setQueryFilter(filter: String){
        savedStateHandle.set(KEY_FILTER, filter)
    }

    companion object {
        private const val KEY_SORT_BY_NAME ="sort_by_name"
        private const val KEY_SORT_BY_PRICE = "sort_by_price"
        private const val KEY_FILTER ="query_filter"
    }
}