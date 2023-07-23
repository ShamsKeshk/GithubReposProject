package com.example.repositoriescoremodule.data.repo

import com.example.repositoriescoremodule.data.local.LocalGitHupRepository
import com.example.repositoriescoremodule.data.remote.RemoteGitHupRepository
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import com.example.repositoriescoremodule.domain.repositories.GitHupRepository

class GitHupRepositoryImpl(private val remoteGitHupRepository: RemoteGitHupRepository,
                           private val localGitHupRepository: LocalGitHupRepository): GitHupRepository {


    override suspend fun syncRepositories(isForceFetch: Boolean): List<GitHupRepositoryModel>? {
        val cachedData = localGitHupRepository.getRepositories()

        if (!cachedData.isNullOrEmpty() && !isForceFetch)
            return cachedData

        val remoteData = remoteGitHupRepository.fetchRepositories()

        localGitHupRepository.insertRepositories(remoteData)

        return localGitHupRepository.getRepositories()
    }
}