package com.pedrofr.androidchallengewit.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.pedrofr.androidchallengewit.database.AppDatabase
import com.pedrofr.androidchallengewit.database.model.City
import com.pedrofr.androidchallengewit.utils.CITIES_DATA_JSON_FILENAME

class CitiesDatabaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "CitiesDatabaseWorker"
    }

    override suspend fun doWork(): Result {
        try {
//            val jsonString = applicationContext.assets.readFile("file_name.json")
//
//            val moshi: Moshi = Moshi.Builder().build()
//            val adapter: JsonAdapter<City> = moshi.adapter(City::class.java)
//            val movieList: List<City> = adapter.fromJson(jsonString) //TODO see if there's a need to handle this warning

//            String cardsJsonResponse = ...;
//            Type type = Types.newParameterizedType(List.class, Card.class);
//            JsonAdapter<List<Card>> adapter = moshi.adapter(type);
//            List<Card> cards = adapter.fromJson(cardsJsonResponse);

            applicationContext.assets.open(CITIES_DATA_JSON_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val cityType = object : TypeToken<List<City>>() {}.type
                    val cityList: List<City> = Gson().fromJson(jsonReader, cityType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.cityDao().insertAll(cityList)

                    return Result.success()
                }
            }
        } catch (error: Exception) {
            Log.e(TAG, "Error when saving cities in database", error)
            return Result.failure()
        }
    }
}