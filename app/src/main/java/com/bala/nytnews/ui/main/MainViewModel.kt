package com.bala.nytnews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bala.nytnews.ui.main.data.NewsItem
import com.bala.nytnews.ui.main.paging.NewsItemRemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    fun getNewsItems(): Flow<PagingData<NewsItem>> {
        return NewsItemRemoteDataSourceImpl.getNewsItems().cachedIn(viewModelScope)
    }
}