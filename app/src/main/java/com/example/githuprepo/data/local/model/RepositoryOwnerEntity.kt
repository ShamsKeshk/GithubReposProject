package com.example.githuprepo.data.local.model

import com.example.githuprepo.data.remote.model.RemoteRepositoryOwner
import com.example.repositoriescoremodule.domain.models.RepositoryOwner

data class RepositoryOwnerEntity(val ownerId: Int,
                                 val ownerName: String,
                                 val avatarUrl: String?) {

    companion object{
         fun toEntity(remoteRepositoryOwner: RemoteRepositoryOwner): RepositoryOwnerEntity {
             return remoteRepositoryOwner.let {
                 RepositoryOwnerEntity(it.id , it.login,it.avatar_url)
             }
         }

        fun toEntity(remoteRepositoryOwner: RepositoryOwner): RepositoryOwnerEntity {
            return remoteRepositoryOwner.let {
                RepositoryOwnerEntity(it.id , it.name,it.avatarUrl)
            }
        }
    }
    fun asDomain(): RepositoryOwner{
        return RepositoryOwner(ownerId,ownerName,avatarUrl)
    }
}