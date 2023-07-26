package com.example.githuprepo.data.remote.services

import com.example.githuprepo.data.remote.model.GitHupRepositoryServerResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHupRepoRemoteService {

    //https://api.github.com/search/repositories?q=language=+&sort=stars
    //@GET("/search/repositories?q=language=+sort:stars") Requested URL

    @Headers("Accept: application/json","Content-Type: application/json")
    @GET("/search/repositories")
    suspend fun syncRepositories(
        @Query("q") search: String?,
        @Query("sort") sort: String? )
    : GitHupRepositoryServerResponse
}