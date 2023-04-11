package com.example.deazer2.repository

import com.example.deazer2.api.MainEndpoint
import com.example.deazer2.model.Album
import com.example.deazer2.model.AlbumListResponse

class MainRepository(
    private val mainEndpoint: MainEndpoint
) {

    suspend fun getAll(): AlbumListResponse {
        return mainEndpoint.getAll()
    }

    suspend fun getAlbumById(id: Long): Album {
        return mainEndpoint.getAlbumById(id)
    }

}