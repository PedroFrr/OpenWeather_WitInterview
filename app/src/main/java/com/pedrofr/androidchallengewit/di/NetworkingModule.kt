package com.pedrofr.androidchallengewit.di

import com.pedrofr.androidchallengewit.networking.OpenWeatherClient
import com.pedrofr.androidchallengewit.networking.OpenWeatherService
import com.pedrofr.androidchallengewit.networking.mapper.ApiMapper
import com.pedrofr.androidchallengewit.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {


    //TODO Add Authorization Interceptor if Token is needed for API

    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherService(retrofit:Retrofit) = retrofit.create(OpenWeatherService::class.java)

    @Provides
    @Singleton
    fun provideOpenWeatherClient(openWeatherService: OpenWeatherService) = OpenWeatherClient(openWeatherService)


}