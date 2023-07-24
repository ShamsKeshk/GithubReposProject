package com.example.githuprepo.data.remote.services

import com.example.githuprepo.data.remote.model.GitHupRepositoryServerResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitHupRepoRemoteService {

    //https://api.github.com/search/repositories?q=language=+&sort=stars

    @Headers("Accept: application/json","Content-Type: application/json")
    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun syncRepositories()
    : GitHupRepositoryServerResponse
}