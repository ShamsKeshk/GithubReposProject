package com.example.githuprepo.data.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.githuprepo.data.local.model.RepositoryModelEntity
import com.example.githuprepo.data.local.model.RepositoryOwnerEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
internal class RepositoriesDatabaseTest{

    private val fakeRepo = RepositoryModelEntity(123,
        "Unit test DB","Run Unit test for database in memory instead if Android testing that need AVD or real device",
        "JUnit",7, RepositoryOwnerEntity(10,"Shams Keshk")
    )

    private lateinit var database: RepositoriesDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initializeDatabase() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RepositoriesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun saveRepo_andRetrieveItAgain() = runTest{
        database.getRepositoriesDao().insertRepo(fakeRepo)

        val repoEntity = database.getRepositoriesDao().getRepoById(fakeRepo.id)
        assertEquals(fakeRepo.name,repoEntity.name)
    }

    @Test
    fun saveRepo_andDeleteItAgain() = runTest{
        database.getRepositoriesDao().insertRepo(fakeRepo)

        val repoEntity = database.getRepositoriesDao().getRepoById(fakeRepo.id)
        assertEquals(fakeRepo.name,repoEntity.name)

        database.getRepositoriesDao().deleteCache()

        val repositories = database.getRepositoriesDao().getRepositories()?.size

        assertEquals(repositories,0)

    }

    @Test
    fun getRepo_withInvalidId_shouldReturnNull() = runTest{

        val reminderEntity = database.getRepositoriesDao().getRepoById(fakeRepo.id)
        assertEquals(null,reminderEntity?.name)
    }

    @After
    fun closeDatabaseConnection(){
        database.close()
    }

}