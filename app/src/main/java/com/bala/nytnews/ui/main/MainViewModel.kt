package com.bala.nytnews.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bala.nytnews.ui.main.data.NewsItem
import com.bala.nytnews.ui.main.paging.NewsItemRemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun getNewsItems(): Flow<PagingData<NewsItem>> {
        return NewsItemRemoteDataSourceImpl.getNewsItems().cachedIn(viewModelScope)
    }

    fun setIsGrid(isGrid: Boolean) {
        savedStateHandle.set(KEY_IS_GRID, isGrid)
    }

    fun getIsGrid(): Boolean {

        savedStateHandle.get<Boolean>(KEY_IS_GRID)?.let { return it }

        return false
    }

    companion object {
        private const val KEY_IS_GRID = "isGrid"
    }
}