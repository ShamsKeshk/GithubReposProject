package com.example.repositoriescoremodule.data.local

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface LocalGitHupDatasource {


    suspend fun getRepositories(): List<GitHupRepositoryModel>?

    suspend fun insertRepositories(repositories: List<GitHupRepositoryModel>)

    suspend fun deleteCache()
}