package com.example.githuprepo.data.remote.di

import com.example.githuprepo.data.remote.services.GitHupRepoRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteGitHubRepoModule {

    @Singleton
    @Provides
    fun getGitHupRepoRemoteService(retrofit: Retrofit): GitHupRepoRemoteService {
        return retrofit.create(GitHupRepoRemoteService::class.java)
    }
}