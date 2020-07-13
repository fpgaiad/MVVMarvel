package com.mvvmarvel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mvvmarvel.R
import com.mvvmarvel.data.model.MarvelCharacter
import com.squareup.picasso.Picasso

class CharactersAdapter(
    var context: Context?,
    var marvelCharacters: MutableList<MarvelCharacter>,
    var cardCharacterListener: (MarvelCharacter) -> Unit,
    var saveFavoriteListener: (MarvelCharacter) -> Unit,
    var deleteFavoriteListener: (MarvelCharacter) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private var favoriteMap = mutableMapOf<Int, Boolean>()

    companion object {
        const val IMAGE_SIZE = "/portrait_fantastic."
    }

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

        holder.cardCharacter.setOnClickListener { cardCharacterListener(character) }
        holder.favoriteImageView.setOnClickListener {
            onFavoriteClick(character, holder.favoriteImageView)
        }

        val imgRes = getFavoriteIconResource(character.id)
        holder.favoriteImageView.setImageResource(imgRes)

        val imagePath = character.thumbnail.path.replace("http", "https")
        val imageExtension = character.thumbnail.extension
        Picasso.get()
            .load("$imagePath$IMAGE_SIZE$imageExtension")
            .placeholder(R.drawable.placeholder)
            .into(holder.imageThumbnail)
    }

    private fun getFavoriteIconResource(id: Int): Int {
        return if (favoriteMap.containsKey(id))
            R.drawable.ic_favorite_full else R.drawable.ic_favorite_border
    }

    private fun onFavoriteClick(character: MarvelCharacter, imageView: ImageView) {
        val imgRes = if (favoriteMap.containsKey(character.id))
            R.drawable.ic_favorite_border else R.drawable.ic_favorite_full
        imageView.setImageResource(imgRes)

        if (favoriteMap.containsKey(character.id)) {
            deleteFavoriteListener.invoke(character)
            favoriteMap.remove(character.id)
        } else {
            saveFavoriteListener.invoke(character)
            favoriteMap[character.id] = true
        }
    }

    class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        var favoriteImageView: ImageView = view.findViewById(R.id.iv_favorite)
        val imageThumbnail: ImageView = view.findViewById(R.id.iv_character)
        val cardCharacter: CardView = view.findViewById(R.id.card_character)
    }

    fun getFavorites(): Map<Int, Boolean> {
        return favoriteMap
    }

    fun addCharacters(characters: List<MarvelCharacter>) {
        val startPosition = marvelCharacters.size
        marvelCharacters.addAll(characters)
        notifyItemInserted(startPosition)
    }

    fun clearList() {
        marvelCharacters = mutableListOf()
        notifyDataSetChanged()
    }

    fun setFavoriteList(favorites: List<MarvelCharacter>) {
        favoriteMap = favorites
            .associate { it.id to true }
            .toMutableMap()
    }

    fun setFavorite(favorite: MarvelCharacter) {
        favoriteMap[favorite.id] = true
        notifyDataSetChanged()
    }

    fun removeFavorite(marvelCharacter: MarvelCharacter) {
        favoriteMap.remove(marvelCharacter.id)
        notifyDataSetChanged()
    }
}