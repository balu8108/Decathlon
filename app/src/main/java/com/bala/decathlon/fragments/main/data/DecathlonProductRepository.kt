package com.bala.decathlon.fragments.main.data

import androidx.paging.*
import com.bala.decathlon.AppDatabase
import com.bala.decathlon.fragments.main.paging.DecathlonProductMediator
import kotlinx.coroutines.flow.Flow

/**
 * repository class to manage the data flow and map it if needed
 */
@ExperimentalPagingApi
class DecathlonProductRepository {

    companion object {
        const val DEFAULT_PAGE_SIZE = 9

        fun getInstance() = DecathlonProductRepository()
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    fun letDecathlonProductsDb(pagingConfig: PagingConfig = getDefaultPageConfig(), sortByPrice: Boolean, sortByName: Boolean, queryFilter: String): Flow<PagingData<DecathlonProduct>> {

        val lPagingSourceConfig = {
            if (sortByName)
                AppDatabase.getInstance().getDecathlonProductDao().getAllDecathlonProductsSortedByName(queryFilter)
            else if (sortByPrice)
                AppDatabase.getInstance().getDecathlonProductDao().getAllDecathlonProductsSortedByPrice(queryFilter)
            else
                AppDatabase.getInstance().getDecathlonProductDao().getAllDecathlonProducts(queryFilter)
        }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = lPagingSourceConfig,
            remoteMediator = DecathlonProductMediator()
        ).flow
    }

}