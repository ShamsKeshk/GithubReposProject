package com.example.githuprepo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repositoriescoremodule.domain.models.FilterCriteria
import com.example.repositoriescoremodule.domain.models.GitHupRepositoryModel
import com.example.repositoriescoremodule.domain.models.RepoSearchCriteria
import com.example.repositoriescoremodule.domain.models.RepoSortCriteria
import com.example.repositoriescoremodule.domain.usecases.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception
import com.example.githuprepo.presentation.model.Result

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
     private val getRepositoriesUseCase: GetRepositoriesUseCase): ViewModel() {

    private val _liveData = MutableLiveData<Result<List<GitHupRepositoryModel>?>>()

    fun repositoriesLiveData(): LiveData<Result<List<GitHupRepositoryModel>?>> = _liveData

    init {
        syncRepositories()
    }


    @JvmOverloads
    fun syncRepositories(isForceFetch: Boolean = false){
        _liveData.value = Result.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val filterCriteria = FilterCriteria(RepoSearchCriteria.LANGUAGE.value,
                    RepoSortCriteria.STARS.value)

               val data = getRepositoriesUseCase.invoke(isForceFetch, filterCriteria)

                _liveData.postValue(Result.Success(data))
            }catch (e: Exception){
                _liveData.postValue(Result.Error(e))
            }

        }
    }
}