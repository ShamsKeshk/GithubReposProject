package com.example.repositoriescoremodule.data.local

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

internal class FakeLocalGitHupDatasourceImpl: LocalGitHupDatasource {

    private val gitHupRepositories = mutableListOf<GitHupRepositoryModel>()

    override suspend fun getRepositories(): List<GitHupRepositoryModel>? {
        return gitHupRepositories
    }

    override suspend fun insertRepositories(repositories: List<GitHupRepositoryModel>) {
        deleteCache()
        gitHupRepositories.addAll(repositories)
    }

    override suspend fun deleteCache() {
        gitHupRepositories.clear()
    }

    fun removeCachedRepo(gitHupRepositoryModel: GitHupRepositoryModel) {
        gitHupRepositories.removeIf {
            it.name == gitHupRepositoryModel.name
        }
    }

}