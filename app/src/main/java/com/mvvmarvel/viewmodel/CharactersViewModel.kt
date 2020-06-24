package com.mvvmarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mvvmarvel.model.Result
import com.mvvmarvel.repository.MarvelRepository

class CharactersViewModel(private val repository: MarvelRepository) : ViewModel() {

    fun getCharacters(): LiveData<List<Result>> {
        return repository.getCharacters()
    }
}