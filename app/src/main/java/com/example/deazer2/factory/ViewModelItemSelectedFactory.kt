package com.example.deazer2.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deazer2.repository.MainRepository
import com.example.deazer2.viewModel.ItemSelectedViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelItemSelectedFactory(
    private val mainFactory: MainRepository,
    private val albumId: Long
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemSelectedViewModel(mainFactory, albumId) as T
    }
}