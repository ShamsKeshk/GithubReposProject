package com.example.repositoriescoremodule.data.remote

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

class FakeRemoteGitHupRepositoryImpl: RemoteGitHupRepository {

    override suspend fun fetchRepositories(): List<GitHupRepositoryModel> {
        return FakeData.getRemoteData()
    }


}