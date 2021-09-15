package com.bala.nytnews.fragments.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bala.nytnews.fragments.main.data.NewsItem
import com.bala.nytnews.fragments.main.data.NewsItemRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val isGrid
        get() = savedStateHandle.getLiveData(KEY_IS_GRID, false)


    /*fun getNewsItems(): Flow<PagingData<NewsItem>> {
        return NewsItemRemoteDataSourceImpl.getNewsItems().cachedIn(viewModelScope)
    }*/
    @ExperimentalPagingApi
    fun getNewsItems(): Flow<PagingData<NewsItem>> {
        return NewsItemRepository.getInstance().letNewsItemsDb().cachedIn(viewModelScope)
    }

    fun setIsGrid(isGrid: Boolean) {
        savedStateHandle.set(KEY_IS_GRID, isGrid)
    }

    companion object {
        private const val KEY_IS_GRID = "isGrid"
    }
}