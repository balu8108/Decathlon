package com.bala.decathlon.fragments.main.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bala.decathlon.AppDatabase
import com.bala.decathlon.fragments.main.apiservice.ApiService
import com.bala.decathlon.fragments.main.data.DecathlonProduct
import com.bala.decathlon.fragments.main.data.RemoteKeys
import java.io.IOException


@ExperimentalPagingApi
class DecathlonProductMediator(private val appDatabase: AppDatabase = AppDatabase.getInstance()) :
    RemoteMediator<Int, DecathlonProduct>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, DecathlonProduct>
    ): MediatorResult {

        val lPage = when (val lPageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return lPageKeyData
            }
            else -> {
                lPageKeyData as Int
            }
        }

        try {
            val lResponse = ApiService.getDecathlonProductsInPage(lPage)
            val lIsEndOfList = lResponse.isEmpty()
            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRepoDao().clearRemoteKeys()
                    appDatabase.getDecathlonProductDao().clearAllDecathlonProducts()
                }
                val lPrevKey = if (lPage == DEFAULT_PAGE_INDEX) null else lPage - 1
                val lNextKey = if (lIsEndOfList) null else lPage + 1
                val lKeys = lResponse.map {
                    RemoteKeys(repoId = it.id, prevKey = lPrevKey, nextKey = lNextKey)
                }
                appDatabase.getRepoDao().insertAll(lKeys)
                appDatabase.getDecathlonProductDao().insertAll(lResponse)
            }
            return MediatorResult.Success(endOfPaginationReached = lIsEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        }
    }

    /**
     * this returns the page key or the final end of list success result
     */
    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, DecathlonProduct>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val lRemoteKeys = getClosestRemoteKey(state)
                lRemoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val lRemoteKeys = getLastRemoteKey(state)
                    ?: return DEFAULT_PAGE_INDEX
                lRemoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val lRemoteKeys = getFirstRemoteKey(state)
                    ?: return DEFAULT_PAGE_INDEX
                //end of list condition reached
                lRemoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                lRemoteKeys.prevKey
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, DecathlonProduct>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { lDecathlonProduct -> appDatabase.getRepoDao().remoteKeysDecathlonProductId(lDecathlonProduct.id) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, DecathlonProduct>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { lDecathlonProduct ->
                appDatabase.getRepoDao().remoteKeysDecathlonProductId(lDecathlonProduct.id) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, DecathlonProduct>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.getRepoDao().remoteKeysDecathlonProductId(repoId)
            }
        }
    }

    companion object
    {
        const val DEFAULT_PAGE_INDEX = 0
    }

}