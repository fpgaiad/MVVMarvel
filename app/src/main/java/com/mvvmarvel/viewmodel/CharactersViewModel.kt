package com.mvvmarvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.repository.MarvelRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val repository: MarvelRepository,
    private val isFavorite: Boolean = false
) : ViewModel() {
    var characters = MutableLiveData<List<MarvelCharacter>>()
    var error = MutableLiveData<Throwable>()
    var disposables = CompositeDisposable()

    fun getCharacters() {
        if (isFavorite) {
            viewModelScope.launch {
                characters = withContext(Dispatchers.IO) {
                    repository.getFavorites()
                }
            }
        } else {
            disposables.add(repository.getCharacters()
                .subscribe(
                    {
                        characters.value = it.data.marvelCharacters
                    }, {
                        error.value = it
                    }
                )
            )
        }
    }


    fun saveCharacter(marvelCharacter: MarvelCharacter) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.saveFavorite(marvelCharacter)
            }
        }
    }

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

}