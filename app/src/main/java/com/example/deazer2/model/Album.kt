package com.example.deazer2.model


data class Album(

    val id: Long,

    val title: String,

    val cover: String,

    val artist: Artist,

    val tracks: Tracks

)


