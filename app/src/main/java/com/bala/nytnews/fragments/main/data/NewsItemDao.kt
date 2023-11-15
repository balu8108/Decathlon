package com.bala.nytnews.fragments.main.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(decathlonProducts: List<DecathlonProduct>)

    @Query("SELECT * FROM decathlonproduct")
    fun getAllNewsItems(): PagingSource<Int, DecathlonProduct>

    @Query("DELETE FROM decathlonproduct")
    suspend fun clearAllNewsItems()
}