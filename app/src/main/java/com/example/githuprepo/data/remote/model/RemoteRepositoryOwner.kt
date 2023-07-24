package com.example.githuprepo.data.remote.model

import com.example.repositoriescoremodule.domain.models.RepositoryOwner

data class RemoteRepositoryOwner(val id: Int,
                                 val login: String) {

    fun asDomain(): RepositoryOwner{
        return RepositoryOwner(id,login)
    }
}