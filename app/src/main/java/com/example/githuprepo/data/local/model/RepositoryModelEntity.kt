package com.example.githuprepo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githuprepo.data.remote.model.RemoteGitHupRepositoryModel
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel

@Entity(tableName = "repositories")
data class RepositoryModelEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String? = null,
    val language: String?= null,
    val stars: Int = 0,
    @Embedded
    val owner: RepositoryOwnerEntity) {

    companion object{

        fun toEntity(remoteGitHupRepositoryModel: RemoteGitHupRepositoryModel): RepositoryModelEntity{
            return remoteGitHupRepositoryModel.let {
                RepositoryModelEntity(it.id,
                    it.name,
                    it.description,
                    it.language,
                    it.stargazers_count,
                    RepositoryOwnerEntity.toEntity(it.owner))
            }
        }

        fun toEntity(remoteGitHupRepositoryModel: GitHupRepositoryModel): RepositoryModelEntity{
            return remoteGitHupRepositoryModel.let {
                RepositoryModelEntity(it.id,
                    it.name,
                    it.details,
                    it.language,
                    it.stars,
                    RepositoryOwnerEntity.toEntity(it.repositoryOwner))
            }
        }
    }

    fun asDomain(): GitHupRepositoryModel{
        return GitHupRepositoryModel(id,name,description,language,stars,owner.asDomain())
    }


}