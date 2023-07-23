package com.example.repositoriescoremodule.data.repo

import com.example.repositoriescoremodule.data.local.FakeLocalGitHupRepositoryImpl
import com.example.repositoriescoremodule.data.remote.FakeRemoteGitHupRepositoryImpl
import com.example.repositoriescoremodule.domain.repositories.GitHupRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test


internal class GitHupRepositoryImplTest {

    private lateinit var fakeLocalGitHupRepositoryImpl: FakeLocalGitHupRepositoryImpl
    private lateinit var fakeRemoteGitHupRepositoryImpl: FakeRemoteGitHupRepositoryImpl

    private lateinit var gitHubGitHupRepository: GitHupRepository

    @Before
    fun initModels(){
        fakeLocalGitHupRepositoryImpl = FakeLocalGitHupRepositoryImpl()
        fakeRemoteGitHupRepositoryImpl = FakeRemoteGitHupRepositoryImpl()

        gitHubGitHupRepository = GitHupRepositoryImpl(fakeRemoteGitHupRepositoryImpl,fakeLocalGitHupRepositoryImpl)
    }



    @Test
    fun syncRepositories_ReturnCorrectRemoteData() = runBlockingTest {
        //When Call
        val data = gitHubGitHupRepository.syncRepositories(false)

        //And have
        val remoteData = fakeRemoteGitHupRepositoryImpl.fetchRepositories()

        //Then
        assertEquals(remoteData[0].name, data!![0].name)
    }

    @Test
    fun removeLocalRepo_CachedDataUpdatedData() = runBlockingTest{
        //When
        val data = gitHubGitHupRepository.syncRepositories(false)
        val oldListSize = data!!.size

        //AND
        fakeLocalGitHupRepositoryImpl.removeCachedRepo(data[0])
        val newRefreshedData = gitHubGitHupRepository.syncRepositories(false)
        val newListSize = newRefreshedData!!.size

        //Then
        assertNotEquals(oldListSize,newListSize)
    }

    @Test
    fun syncRepositories_forceLoad_returnRefreshedData() = runBlockingTest{
        //When
        val data = gitHubGitHupRepository.syncRepositories(false)

        //AND
        fakeLocalGitHupRepositoryImpl.removeCachedRepo(data!![0])
        val oldListSize = data.size

        val newRefreshedData = gitHubGitHupRepository.syncRepositories(true)
        val newListSize = newRefreshedData!!.size

        //Then
        assertNotEquals(oldListSize,newListSize)
    }

    @Test
    fun syncRepositories_returnCachedDataCorrectly() = runBlockingTest{
        //When
        val data = gitHubGitHupRepository.syncRepositories(false)

        //AND
        val oldListSize = data!!.size
        val firstItem = data[0]

        val newRefreshedData = gitHubGitHupRepository.syncRepositories(false)
        val cachedDataSize = newRefreshedData!!.size
        val cachedDataFirstRepo = newRefreshedData[0]

        //Then
        assertEquals(oldListSize,cachedDataSize)
        assertEquals(firstItem.name,cachedDataFirstRepo.name)
    }
}