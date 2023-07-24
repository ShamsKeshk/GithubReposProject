package com.example.repositoriescoremodule.domain.models

data class GitHupRepositoryModel(
    val id: Int,
    val name: String,
    val details: String? = null,
    val language: String? = null,
    val stars: Int = 0,
    val repositoryOwner: RepositoryOwner) {
}