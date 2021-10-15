package com.example.encoder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encoder.databinding.ItemRowBinding

class Adapter (val phrases: ArrayList<Phrase>): RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    class ItemViewHolder (val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val phrase = phrases[position]
        holder.binding.apply {
            textTv.text = phrase.text
            textTv.setTextColor(phrase.color)
        }
    }

    override fun getItemCount() = phrases.size

    fun update(){
        notifyDataSetChanged()
    }
}