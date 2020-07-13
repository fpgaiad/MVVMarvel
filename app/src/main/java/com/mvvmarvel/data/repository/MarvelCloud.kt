package com.mvvmarvel.data.repository

import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

interface MarvelCloud {

    fun getCharacters(offset: Int): Single<MarvelResponse>

    fun getCharactersByQuery(query: String, offset: Int): Single<MarvelResponse>
}