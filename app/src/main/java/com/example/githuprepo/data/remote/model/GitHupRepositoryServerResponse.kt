package com.example.githuprepo.data.remote.model

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

class GitHupRepositoryServerResponse(private val total_count: Int? = -1,
                                     private val incomplete_results : Boolean? = false,
                                     private val items: List<RemoteGitHupRepositoryModel>? = emptyList()
) {

    fun asDomain(): List<GitHupRepositoryModel>{
        if (items.isNullOrEmpty())
            return emptyList()

        return items.map {
            it.asDomain()
        }
    }
}