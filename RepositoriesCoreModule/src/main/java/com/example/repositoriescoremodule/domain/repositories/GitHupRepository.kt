package com.example.repositoriescoremodule.domain.repositories

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface GitHupRepository {

    suspend fun syncRepositories(isForceFetch: Boolean): List<GitHupRepositoryModel>?
}