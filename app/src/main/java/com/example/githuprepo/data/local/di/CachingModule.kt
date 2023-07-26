package com.example.githuprepo.data.local.di

import android.content.Context
import com.example.githuprepo.data.local.database.RepositoriesDao
import com.example.githuprepo.data.local.database.RepositoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CachingModule {

    @Provides
    fun provideAsteroidsDatabase(@ApplicationContext context: Context): RepositoriesDatabase{
        return RepositoriesDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideAsteroidsDao(asteroidsDatabase: RepositoriesDatabase): RepositoriesDao{
        return asteroidsDatabase.getRepositoriesDao()
    }
}