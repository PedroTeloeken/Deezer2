package com.example.deazer2.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deazer2.repository.MainRepository


import com.example.deazer2.viewModel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelMainFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}