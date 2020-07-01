package com.mvvmarvel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvmarvel.R
import com.mvvmarvel.data.model.MarvelCharacter

class CharactersAdapter(
    var context: Context?,
    var marvelCharacters: List<MarvelCharacter>
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.characters_list_item, parent, false)
        return CharactersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marvelCharacters.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = marvelCharacters[position]

        holder.name.text = character.name
        holder.favoriteIcon.setOnClickListener {

        }

    }

    class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val favoriteIcon: ImageView = view.findViewById(R.id.iv_favorite)
    }
}