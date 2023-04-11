package com.example.deazer2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deazer2.model.Album
import com.example.deazer2.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _albumList = MutableLiveData<List<Album>>()
    val albumList: LiveData<List<Album>> = _albumList

    init {
         getAllAlbums()
    }

    private fun getAllAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = mainRepository.getAll()
            withContext(Dispatchers.Main){
                _albumList.value = result.data
            }
        }
    }
}
