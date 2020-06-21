package com.mvvmarvel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mvvmarvel.R
import com.mvvmarvel.viewmodel.CharactersViewModel

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel

    companion object {
        private const val IS_FAV_KEY = "is_favorite_key"

        fun newInstance(isFavorites: Boolean = false) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_FAV_KEY, isFavorites)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
    }
}