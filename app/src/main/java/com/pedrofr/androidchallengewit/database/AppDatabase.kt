package com.pedrofr.androidchallengewit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.pedrofr.androidchallengewit.database.dao.CityDao
import com.pedrofr.androidchallengewit.database.dao.WeatherDao
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.database.model.Weather
import com.pedrofr.androidchallengewit.utils.DATABASE_NAME
import com.pedrofr.androidchallengewit.workers.CitiesDatabaseWorker

/**
 * SQLite Database for storing the logs.
 */
@Database(entities = [Weather::class, City::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //Adding callback to pre populate the database with the city list from JSON
        //For a better solution this city list should be hosted in a remote server so I wouldn't have to do this
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<CitiesDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}