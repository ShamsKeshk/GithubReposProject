package com.example.repositoriescoremodule.data.repo

import com.example.repositoriescoremodule.data.local.LocalGitHupDatasource
import com.example.repositoriescoremodule.data.remote.RemoteGitHupDatasource
import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import com.example.repositoriescoremodule.domain.repositories.GitHupRepository

class GitHupRepositoryImpl(private val remoteGitHupDatasource: RemoteGitHupDatasource,
                           private val localGitHupDatasource: LocalGitHupDatasource): GitHupRepository {


    override suspend fun syncRepositories(isForceFetch: Boolean,filterCriteria: FilterCriteria): List<GitHupRepositoryModel>? {
        val cachedData = localGitHupDatasource.getRepositories()

        if (!cachedData.isNullOrEmpty() && !isForceFetch)
            return cachedData
        else if (!cachedData.isNullOrEmpty()){
            localGitHupDatasource.deleteCache()
        }

        val remoteData = remoteGitHupDatasource.fetchRepositories()

        localGitHupDatasource.insertRepositories(remoteData)

        return localGitHupDatasource.getRepositories()
    }
}