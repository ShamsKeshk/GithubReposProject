package com.example.repositoriescoremodule.data.remote

import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

class FakeRemoteGitHupDatasourceImpl: RemoteGitHupDatasource {

    override suspend fun fetchRepositories(filterCriteria: FilterCriteria): List<GitHupRepositoryModel> {
        return FakeData.getRemoteData()
    }


}