package com.example.githuprepo.domain.di

import com.example.githuprepo.data.local.dataSource.LocalGitHupDatasourceImpl
import com.example.githuprepo.data.remote.dataSource.RemoteGitHupDatasourceImpl
import com.example.repositoriescoremodule.data.repo.GitHupRepositoryImpl
import com.example.repositoriescoremodule.domain.repositories.GitHupRepository
import com.example.repositoriescoremodule.domain.usecases.GetRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesDomainModule {

    @Singleton
    @Provides
    fun provideRepositoryImplementation(
        remoteGitHupDatasourceImpl: RemoteGitHupDatasourceImpl,
        localGitHupDatasourceImpl: LocalGitHupDatasourceImpl): GitHupRepository {
        return GitHupRepositoryImpl(remoteGitHupDatasourceImpl,localGitHupDatasourceImpl)
    }


    @Singleton
    @Provides
    fun provideGetRepositoriesUseCase(gitHupDatasource: GitHupRepository): GetRepositoriesUseCase {
        return GetRepositoriesUseCase(gitHupDatasource)
    }
}