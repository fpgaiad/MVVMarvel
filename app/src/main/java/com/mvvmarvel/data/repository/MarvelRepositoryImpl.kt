package com.mvvmarvel.data.repository

import com.mvvmarvel.data.database.FavoriteDao
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

class MarvelRepositoryImpl(
    private val cloud: MarvelCloud,
    private val store: FavoriteDao
) : MarvelRepository {

    override fun getCharacters(offset: Int): Single<MarvelResponse> {
        return cloud.getCharacters(offset)
    }

    override fun getCharactersByQuery(query: String, offset: Int): Single<MarvelResponse> {
        return cloud.getCharactersByQuery(query, offset)
    }

    override suspend fun getFavorite(id: Int): MarvelCharacter {
        return store.getFavoriteById(id)
    }

    override suspend fun getFavorites(): List<MarvelCharacter> {
        return store.getFavorites()
    }

    override suspend fun saveFavorite(character: MarvelCharacter): Long {
        return store.insertOrUpdateFavorite(character)
    }

    override suspend fun deleteFavorite(character: MarvelCharacter): Int {
        return store.deleteFavorite(character)
    }
}