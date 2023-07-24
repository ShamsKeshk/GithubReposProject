package com.example.githuprepo.data.local.model

import com.example.githuprepo.data.remote.model.RemoteRepositoryOwner
import com.example.repositoriescoremodule.domain.models.RepositoryOwner

data class RepositoryOwnerEntity(val ownerId: Int,
                                 val ownerName: String) {

    companion object{
         fun toEntity(remoteRepositoryOwner: RemoteRepositoryOwner): RepositoryOwnerEntity {
             return remoteRepositoryOwner.let {
                 RepositoryOwnerEntity(it.id , it.login)
             }
         }

        fun toEntity(remoteRepositoryOwner: RepositoryOwner): RepositoryOwnerEntity {
            return remoteRepositoryOwner.let {
                RepositoryOwnerEntity(it.id , it.name)
            }
        }
    }
    fun asDomain(): RepositoryOwner{
        return RepositoryOwner(ownerId,ownerName)
    }
}