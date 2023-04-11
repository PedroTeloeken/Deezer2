package com.example.deazer2.activity

import android.media.AudioAttributes
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.deazer2.R
import com.example.deazer2.adapter.ItemSelectedAdapter
import com.example.deazer2.api.MainEndpoint
import com.example.deazer2.api.RetrofitIni
import com.example.deazer2.databinding.ActivityAlbumSelectedBinding
import com.example.deazer2.factory.ViewModelItemSelectedFactory
import com.example.deazer2.model.TrackData
import com.example.deazer2.repository.MainRepository
import com.example.deazer2.viewModel.ItemSelectedViewModel

private const val ID_ALBUM = "id"

class ItemSelectedActivity : AppCompatActivity(R.layout.activity_album_selected) {

    private val binding by lazy {
        ActivityAlbumSelectedBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val refactor = ViewModelItemSelectedFactory(
            MainRepository(RetrofitIni.retrofit.create(MainEndpoint::class.java)),
            intent.getLongExtra(ID_ALBUM, 0)
        )
        val provider = ViewModelProvider(this, refactor)
        provider[ItemSelectedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.album.observe(this) { album ->
            val recyclerView = binding.recyclerViewMusic
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter =
                ItemSelectedAdapter(album.tracks.data, this, onclick = {
                    playMusic(it)
                })
            binding.imageView2.load(album.cover)
            binding.title.text = album.title
            binding.nameArtist.text = album.artist.name
        }

    }

    private fun playMusic(trackData: TrackData) {
        val mediaPlayer = MediaPlayer()

        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        } else {
            val music = trackData.preview
            Log.e("MUSICA RODANDO", music)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()

            mediaPlayer.setAudioAttributes(audioAttributes)

            try {
                mediaPlayer.setDataSource(music)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
        }
    }
}

