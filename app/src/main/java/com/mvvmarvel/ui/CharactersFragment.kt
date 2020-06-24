package com.mvvmarvel.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvmarvel.R
import com.mvvmarvel.viewmodel.CharactersViewModel
import com.mvvmarvel.viewmodel.CharactersViewModelFactory
import kotlinx.android.synthetic.main.characters_fragment.*

class CharactersFragment : Fragment() {

    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var charactersAdapter: CharactersAdapter

    companion object {
        private const val IS_FAV = "is_favorite"

        fun newInstance(isFavorites: Boolean = false) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_FAV, isFavorites)
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

        val charactersViewModelFactory =
            CharactersViewModelFactory(arguments?.getBoolean(IS_FAV) ?: false)

        charactersViewModel = ViewModelProvider(this, charactersViewModelFactory)
            .get(CharactersViewModel::class.java)
        charactersViewModel.getCharacters().observe(viewLifecycleOwner, Observer {
            charactersAdapter.notifyDataSetChanged()
        })

        charactersAdapter = CharactersAdapter(
            context,
            charactersViewModel.getCharacters().value ?: emptyList()
        )

        rv_characters.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(
                context,
                getSpanCount(activity?.resources?.configuration?.orientation)
            )
        }
    }

    private fun getSpanCount(orientation: Int?): Int {
        return when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }
    }
}