package com.example.githuprepo.data.remote.dataSource

import com.example.githuprepo.data.remote.services.GitHupRepoRemoteService
import com.example.repositoriescoremodule.data.remote.RemoteGitHupDatasource
import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import javax.inject.Inject

class RemoteGitHupDatasourceImpl @Inject constructor(
     private val gitHupRepoRemoteService: GitHupRepoRemoteService): RemoteGitHupDatasource {

    override suspend fun fetchRepositories(filterCriteria: FilterCriteria): List<GitHupRepositoryModel> {
        return gitHupRepoRemoteService.syncRepositories()
            .asDomain()
    }
}