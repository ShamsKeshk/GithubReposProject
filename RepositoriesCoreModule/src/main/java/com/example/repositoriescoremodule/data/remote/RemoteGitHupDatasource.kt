package com.example.repositoriescoremodule.data.remote

import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

interface RemoteGitHupDatasource {
    suspend fun fetchRepositories(filterCriteria: FilterCriteria = FilterCriteria()): List<GitHupRepositoryModel>
}