package com.mvvmarvel.repository

import androidx.lifecycle.MutableLiveData
import com.mvvmarvel.model.Result

interface MarvelRepository {

    fun getCharacters(): MutableLiveData<List<Result>>
}