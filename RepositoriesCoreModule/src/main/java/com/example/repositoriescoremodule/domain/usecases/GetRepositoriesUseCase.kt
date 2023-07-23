package com.example.repositoriescoremodule.domain.usecases

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import com.example.repositoriescoremodule.domain.repositories.GitHupRepository

class GetRepositoriesUseCase(private val gitHupRepository: GitHupRepository) {

    suspend fun invoke(isForceFetch: Boolean): List<GitHupRepositoryModel> ?{
        return gitHupRepository.syncRepositories(isForceFetch)
    }
}