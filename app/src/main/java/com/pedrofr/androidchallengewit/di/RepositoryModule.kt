package com.pedrofr.androidchallengewit.di

import com.pedrofr.androidchallengewit.repository.Repository
import com.pedrofr.androidchallengewit.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        impl: RepositoryImpl
    ) : Repository
}