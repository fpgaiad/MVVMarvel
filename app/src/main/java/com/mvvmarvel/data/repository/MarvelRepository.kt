package com.mvvmarvel.data.repository

import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

interface MarvelRepository {

    fun getCharacters(offset: Int): Single<MarvelResponse>

    fun getCharactersByQuery(query: String, offset: Int): Single<MarvelResponse>

    suspend fun getFavorite(id: Int): MarvelCharacter

    suspend fun getFavorites(): List<MarvelCharacter>

    suspend fun saveFavorite(character: MarvelCharacter): Long

    suspend fun deleteFavorite(character: MarvelCharacter): Int
}