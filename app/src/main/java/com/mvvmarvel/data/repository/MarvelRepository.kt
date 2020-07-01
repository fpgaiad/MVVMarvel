package com.mvvmarvel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmarvel.data.model.MarvelResponse
import com.mvvmarvel.data.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {

    fun getCharacters(): Single<MarvelResponse>

    suspend fun getFavorites(): MutableLiveData<List<MarvelCharacter>>

    suspend fun saveFavorite(character: MarvelCharacter)
}