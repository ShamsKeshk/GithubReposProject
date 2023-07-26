package com.example.githuprepo.data.local.convertors

import androidx.room.TypeConverter
import com.example.githuprepo.data.local.model.RepositoryModelEntity
import com.example.githuprepo.data.local.model.RepositoryOwnerEntity
import com.google.gson.Gson

class RepositoryModelConvertor {


    @TypeConverter
    fun fromJsonToRepositoryModel(json: String?): RepositoryModelEntity? {
        if (json.isNullOrEmpty())
            return null

        return Gson().fromJson(json,RepositoryModelEntity::class.java)
    }

    @TypeConverter
    fun toJsonFromRepositoryModelEntity(repositoryModelEntity: RepositoryModelEntity?): String? {
        if (repositoryModelEntity == null)
            return null

        return Gson().toJson(repositoryModelEntity)
    }

    @TypeConverter
    fun fromJsonToRepositoryOwner(json: String?): RepositoryOwnerEntity? {
        if (json.isNullOrEmpty())
            return null

        return Gson().fromJson(json,RepositoryOwnerEntity::class.java)
    }

    @TypeConverter
    fun toJsonFromRepositoryOwnerEntity(repositoryOwnerEntity: RepositoryOwnerEntity?): String? {
        if (repositoryOwnerEntity == null)
            return null

        return Gson().toJson(repositoryOwnerEntity)
    }
}