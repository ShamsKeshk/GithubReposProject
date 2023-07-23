package com.example.repositoriescoremodule.domain.models

data class GitHupRepositoryModel(val name: String,
                                 val details: String? = null,
                                 val language: String? = null,
                                 val rate: Float? = 0f,
                                 val repositoryOwner: RepositoryOwner) {
}