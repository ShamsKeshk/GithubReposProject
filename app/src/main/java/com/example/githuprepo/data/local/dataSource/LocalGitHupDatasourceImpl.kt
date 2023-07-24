package com.example.githuprepo.data.local.dataSource

import com.example.githuprepo.data.local.database.RepositoriesDao
import com.example.githuprepo.data.local.model.RepositoryModelEntity
import com.example.repositoriescoremodule.data.local.LocalGitHupDatasource
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import javax.inject.Inject

class LocalGitHupDatasourceImpl @Inject constructor(private val repositoriesDao: RepositoriesDao): LocalGitHupDatasource {

    override suspend fun getRepositories(): List<GitHupRepositoryModel>? {
        return repositoriesDao.getRepositories()?.map {
            it.asDomain()
        }
    }

    override suspend fun insertRepositories(repositories: List<GitHupRepositoryModel>) {
        val listOfRepositories = repositories.map {
            RepositoryModelEntity.toEntity(it)
        }.toTypedArray()


        repositoriesDao.insertAll(*listOfRepositories)
    }

    override suspend fun deleteCache() {
       repositoriesDao.deleteCache()
    }
}