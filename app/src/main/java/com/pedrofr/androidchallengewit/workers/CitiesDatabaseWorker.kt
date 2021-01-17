package com.pedrofr.androidchallengewit.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pedrofr.androidchallengewit.database.AppDatabase
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.utils.CITIES_DATA_JSON_FILENAME
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Suppress("BlockingMethodInNonBlockingContext")
class CitiesDatabaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "CitiesDatabaseWorker"
    }

    override suspend fun doWork(): Result {
        try {
            val jsonString = applicationContext.assets.open(CITIES_DATA_JSON_FILENAME).bufferedReader().use { it.readText() }
            val moshi = Moshi.Builder().build()
            val type = Types.newParameterizedType(List::class.java, City::class.java)
            val jsonAdapter: JsonAdapter<List<City>> = moshi.adapter(type)
            val cityList = jsonAdapter.fromJson(jsonString)

            cityList?.let{cities ->
                val database = AppDatabase.getInstance(applicationContext)
                database.cityDao().insertAll(cities)
                return Result.success()
            } ?: return Result.failure()


        } catch (error: Exception) {
            Log.e(TAG, "Error when saving cities in database", error)
            return Result.failure()
        }
    }
}