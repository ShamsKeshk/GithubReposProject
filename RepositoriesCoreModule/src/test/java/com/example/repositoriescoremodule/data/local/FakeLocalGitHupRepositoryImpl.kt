package com.example.repositoriescoremodule.data.local

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

internal class FakeLocalGitHupRepositoryImpl: LocalGitHupRepository {

    private val gitHupRepositories = mutableListOf<GitHupRepositoryModel>()

    override suspend fun getRepositories(): List<GitHupRepositoryModel>? {
        return gitHupRepositories
    }

    override suspend fun insertRepositories(repositories: List<GitHupRepositoryModel>) {
        gitHupRepositories.clear()
        gitHupRepositories.addAll(repositories)
    }

    fun removeCachedRepo(gitHupRepositoryModel: GitHupRepositoryModel) {
        gitHupRepositories.removeIf {
            it.name == gitHupRepositoryModel.name
        }
    }

}