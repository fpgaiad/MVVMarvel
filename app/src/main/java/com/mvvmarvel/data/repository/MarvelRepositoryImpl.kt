package com.mvvmarvel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmarvel.data.database.FavoriteDao
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

class MarvelRepositoryImpl(
    private val cloud: MarvelCloud,
    private val store: FavoriteDao
): MarvelRepository {

    override fun getCharacters(): Single<MarvelResponse> {
        return cloud.getCharacters()
    }

    override suspend fun getFavorites(): MutableLiveData<List<MarvelCharacter>> {
        return MutableLiveData(store.getFavorites())
    }

    override suspend fun saveFavorite(character: MarvelCharacter) {
        store.insertOrUpdateFavorite(character)
    }
}