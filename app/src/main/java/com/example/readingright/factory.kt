package com.example.readingright

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewFactory(val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Homeviewmodel(repo) as T
    }
}