package com.mvvmarvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvmarvel.repository.MarvelApiRepository
import com.mvvmarvel.repository.MarvelFavoriteRepository
import com.mvvmarvel.repository.MarvelRepository

class CharactersViewModelFactory(private val isFavorite: Boolean) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = when (isFavorite) {
            true -> MarvelFavoriteRepository
            false -> MarvelApiRepository
        }
        return modelClass.getConstructor(MarvelRepository::class.java)
            .newInstance(repository)
    }
}