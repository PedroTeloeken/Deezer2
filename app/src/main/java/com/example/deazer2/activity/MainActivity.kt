package com.example.deazer2.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deazer2.adapter.ListMusicAdapter
import com.example.deazer2.api.MainEndpoint
import com.example.deazer2.api.RetrofitIni
import com.example.deazer2.databinding.ActivityMainBinding
import com.example.deazer2.factory.ViewModelMainFactory
import com.example.deazer2.model.Album
import com.example.deazer2.repository.MainRepository
import com.example.deazer2.viewModel.MainViewModel
import kotlinx.coroutines.launch


private const val ID_ALBUM = "id"

class MainActivity() : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val refactory = ViewModelMainFactory(MainRepository(RetrofitIni.retrofit.create(MainEndpoint::class.java)))
        val provider = ViewModelProvider(this, refactory)
        provider.get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startList()
    }

    private fun startList() {
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = gridLayoutManager
        viewModel.albumList.observe(this) { albumList ->
            recyclerView.adapter = ListMusicAdapter(albumList , this@MainActivity, onclick = {
                goToAlbumSelected(it)
            })
        }
    }

    private fun goToAlbumSelected(album: Album) {
        val intent = Intent(this@MainActivity, ItemSelectedActivity::class.java)
        intent.putExtra(ID_ALBUM, album.id)
        startActivity(intent)
    }
}