package com.example.repositoriescoremodule.data.remote

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface RemoteGitHupRepository {

    suspend fun fetchRepositories(): List<GitHupRepositoryModel>
}