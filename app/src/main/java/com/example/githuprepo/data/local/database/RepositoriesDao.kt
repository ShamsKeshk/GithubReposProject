package com.example.githuprepo.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githuprepo.data.local.model.RepositoryModelEntity

@Dao
interface RepositoriesDao {

    @Query("SELECT * FROM repositories WHERE id = :repoId")
    suspend fun getRepoById(repoId: Int): RepositoryModelEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(nearEarthEntity: RepositoryModelEntity)

    @Query("DELETE from repositories")
    suspend fun deleteCache()

    @Query("select * from repositories")
    fun getRepositories(): List<RepositoryModelEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg nearEarthEntity: RepositoryModelEntity)
}