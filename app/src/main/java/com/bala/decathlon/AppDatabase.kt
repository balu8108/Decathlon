package com.bala.decathlon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bala.decathlon.fragments.main.data.DecathlonProduct
import com.bala.decathlon.fragments.main.data.DecathlonProductDao
import com.bala.decathlon.fragments.main.data.RemoteKeys
import com.bala.decathlon.fragments.main.data.RemoteKeysDao


@Database(version = 1, entities = [DecathlonProduct::class, RemoteKeys::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getDecathlonProductDao(): DecathlonProductDao

    companion object {

        val DECATHLON_DB = "Decathlon.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(DecathlonProductApplication.application).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DECATHLON_DB)
                .build()
    }

}