package com.example.githuprepo.data.remote.model

import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

data class RemoteGitHupRepositoryModel(val id: Int,
                                       val name: String,
                                       val description: String? = null,
                                       val language: String?= null,
                                       val stargazers_count: Int = 0,
                                       val owner: RemoteRepositoryOwner) {

    fun asDomain(): GitHupRepositoryModel{
        return GitHupRepositoryModel(id,name,description,language,stargazers_count, owner.asDomain())
    }

}