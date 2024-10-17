package com.mobile.newsbuzz.di

import com.mobile.newsbuzz.data.repository.NewsRepositoryImpl
import com.mobile.newsbuzz.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repository: NewsRepositoryImpl): NewsRepository
}
