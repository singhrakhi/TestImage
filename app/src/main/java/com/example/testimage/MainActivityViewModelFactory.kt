package com.example.testimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testimage.repository.RepositoryCallback

class MainActivityViewModelFactory(private var repo:RepositoryCallback):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repo) as T
    }
}