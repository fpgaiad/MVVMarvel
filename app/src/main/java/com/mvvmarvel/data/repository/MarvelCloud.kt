package com.mvvmarvel.data.repository

import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

interface MarvelCloud {

    fun getCharacters(): Single<MarvelResponse>
}