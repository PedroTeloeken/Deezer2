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

class ItemSelectedViewModel(
    private val mainRepository: MainRepository,
    val id: Long
) : ViewModel() {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> = _album

    init {
        getAlbum()
    }

    private fun getAlbum() {
        viewModelScope.launch(Dispatchers.IO) {
            val album = mainRepository.getAlbumById(id)
            withContext(Dispatchers.Main) {
                _album.value = album
            }
        }
    }

}