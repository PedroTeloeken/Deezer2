package com.example.deazer2.api

import com.example.deazer2.model.Album
import com.example.deazer2.model.AlbumListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MainEndpoint {

    @GET("chart/0/albums")
    suspend fun getAll(): AlbumListResponse

    @GET("album/{id}")
    suspend fun getAlbumById(
        @Path("id") id: Long //pode ser int
    ): Album

}