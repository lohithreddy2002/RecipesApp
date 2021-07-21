package com.example.readingright


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readingright.db.Meal
import com.example.readingright.util.Resources
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Homeviewmodel(private val repo: Repo) : ViewModel() {
    val breakingNews: MutableLiveData<Resources<ingredient>> = MutableLiveData()


    init {
        Log.d("response", "it")
        getrandom()
    }

    private fun getrandom() {
        viewModelScope.launch {
            breakingNews.postValue(Resources.Loading())
            kotlin.runCatching {
                repo.getrandom()
            }.onSuccess {
                Log.d("response", "$it")
                breakingNews.postValue(Resources.Success(it))
            }.onFailure {
                breakingNews.postValue(Resources.Failure(it.message.toString()))
            }


        }

    }

    fun insert(item: Meal) {
        viewModelScope.launch {
            repo.insert(item)
        }

    }

    fun getsaved() = repo.getsaved()


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