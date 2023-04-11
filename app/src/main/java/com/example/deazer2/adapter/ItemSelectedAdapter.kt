package com.example.deazer2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deazer2.databinding.ListItemMusicBinding
import com.example.deazer2.model.TrackData

class ItemSelectedAdapter(
    private val data: List<TrackData>,
    private val context: Context,
    private val onclick: (TrackData) -> Unit
) : RecyclerView.Adapter<ItemSelectedAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun link(trackData: TrackData) {
            binding.name.text = trackData.title
            binding.root.setOnClickListener { onclick(trackData) }
            //binding.seiLa.text = data.duration
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemMusicBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        return holder.link(data)
    }
}