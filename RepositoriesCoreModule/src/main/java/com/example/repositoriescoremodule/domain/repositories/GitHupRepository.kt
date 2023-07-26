package com.example.repositoriescoremodule.domain.repositories

import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface GitHupRepository {

    suspend fun syncRepositories(isForceFetch: Boolean,filterCriteria: FilterCriteria = FilterCriteria()): List<GitHupRepositoryModel>?
}