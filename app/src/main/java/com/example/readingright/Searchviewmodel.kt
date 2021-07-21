package com.example.readingright

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readingright.util.Resources
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Searchviewmodel(val repo: Repo) : ViewModel() {

    val query = MutableLiveData<Resources<ingredient>>()

    var job: Job? = null

    fun getsearch(name: String) {
        query.postValue(Resources.Loading())
        viewModelScope.launch {
            kotlin.runCatching {
                repo.search(name)

            }.onSuccess {
                query.postValue(Resources.Success(it))
            }.onFailure {
                query.postValue(Resources.Failure(it.message.toString()))
            }


        }
    }


}