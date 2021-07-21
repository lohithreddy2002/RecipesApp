package com.example.readingright

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchViewfactory(val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Searchviewmodel(repo) as T
    }
}