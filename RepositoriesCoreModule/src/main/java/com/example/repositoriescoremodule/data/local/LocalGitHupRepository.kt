package com.example.repositoriescoremodule.data.local

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface LocalGitHupRepository {


    suspend fun getRepositories(): List<GitHupRepositoryModel>?

    suspend fun insertRepositories(repositories: List<GitHupRepositoryModel>)
}