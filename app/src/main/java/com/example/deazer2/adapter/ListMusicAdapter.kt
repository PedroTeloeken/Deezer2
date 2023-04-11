package com.example.deazer2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.deazer2.databinding.ItemAlbumBinding
import com.example.deazer2.model.Album


class ListMusicAdapter(
    private val listAlbum: List<Album>,
    private val context: Context,
    private val onclick: (Album) -> Unit
) : RecyclerView.Adapter<ListMusicAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun link(album: Album) {
            val singer = binding.textViewNameSinger
            singer.text = album.artist.name
            val albumItem = binding.textViewNameAlbum
            albumItem.text = album.title
            binding.imageView.load(album.cover)
            binding.root.setOnClickListener { onclick(album) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemAlbumBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listAlbum.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = listAlbum[position]
        holder.link(album)
    }
}
