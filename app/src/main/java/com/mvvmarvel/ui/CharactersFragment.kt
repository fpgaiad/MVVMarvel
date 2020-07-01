package com.mvvmarvel.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvmarvel.R
import com.mvvmarvel.databinding.CharactersFragmentBinding
import com.mvvmarvel.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharactersFragment : Fragment() {

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var binding: CharactersFragmentBinding

    private val viewModel: CharactersViewModel by viewModel {
        parametersOf(arguments?.getBoolean(IS_FAV))
    }

    companion object {
        private const val IS_FAV = "is_favorite"

        fun newInstance(isFavorites: Boolean = false) = CharactersFragment().apply {
            arguments = bundleOf(IS_FAV to isFavorites)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.characters_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val orientation = activity?.resources?.configuration?.orientation
        binding.rvCharacters.layoutManager = GridLayoutManager(context, getSpanCount(orientation))

        viewModel.characters.observe(viewLifecycleOwner, Observer { marvelCharacters ->
            charactersAdapter = CharactersAdapter(context, marvelCharacters)
            binding.rvCharacters.adapter = charactersAdapter
            binding.progressBarCharacList.visibility = View.GONE
        })

        fetchCharacters()
    }

    private fun getSpanCount(orientation: Int?): Int {
        return when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }
    }

    private fun fetchCharacters() {
        viewModel.getCharacters()
        binding.progressBarCharacList.visibility = View.VISIBLE
    }
}