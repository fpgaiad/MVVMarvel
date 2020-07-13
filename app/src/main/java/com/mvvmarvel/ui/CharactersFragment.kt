package com.mvvmarvel.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvmarvel.R
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.databinding.CharactersFragmentBinding
import com.mvvmarvel.ui.DetailActivity.Companion.CHARACTER_TAG_ARG
import com.mvvmarvel.ui.DetailActivity.Companion.IS_FAVORITE_CHARACTER_TAG_ARG
import com.mvvmarvel.util.InfiniteScrollListener
import com.mvvmarvel.viewmodel.CharactersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class CharactersFragment : Fragment() {

    companion object {
        private const val IS_FROM_FAVORITE = "is_from_favorite"
        private const val FAVORITE_REQUEST_CODE: Int = 1
        private const val DETAIL_REQUEST_CODE: Int = 2

        fun newInstance(isFromFavoritesActivity: Boolean) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_FROM_FAVORITE, isFromFavoritesActivity)
            }
        }
    }

    private var favorites: List<MarvelCharacter>? = null
    private val viewModel: CharactersViewModel by sharedViewModel {
        parametersOf(isFromFavorites())
    }
    private var charactersAdapter: CharactersAdapter? = null
    private lateinit var binding: CharactersFragmentBinding

    private fun isFromFavorites() = requireArguments().getBoolean(IS_FROM_FAVORITE)

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
        isFromFavorites().let { isFavoriteActivity ->
            when (isFavoriteActivity) {
                true -> setHasOptionsMenu(false)
                false -> setHasOptionsMenu(true)
            }
        }

        setSearchViewToQueryCalls()

        val orientation = activity?.resources?.configuration?.orientation
        val gridLayoutManager = GridLayoutManager(context, getSpanCount(orientation))

        val scrollListener = object : InfiniteScrollListener(
            gridLayoutManager,
            isFromFavorites(),
            viewModel
        ) {
            override fun onLoadMore() {
                binding.progressBarCharacList.visibility = View.VISIBLE
                viewModel.loadMore()
            }
        }

        binding.rvCharacters.apply {
            layoutManager = gridLayoutManager
            addOnScrollListener(scrollListener)
        }

        observeErrors()

        fetchFavoriteList()
        getCharacters()
    }

    private fun observeErrors() {
        viewModel.error.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun fetchFavoriteList() {
        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch(Dispatchers.IO) {
            favorites = viewModel.getFavorites()
            withContext(Dispatchers.Main) {
                favorites?.let {
                    charactersAdapter?.apply {
                        setFavoriteList(it)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun setSearchViewToQueryCalls() {
        binding.searchViewMain.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.apply {
                        charactersAdapter?.clearList()
                        charactersAdapter = null
                        binding.progressBarCharacList.visibility = View.VISIBLE
                        view?.let {
                            hideKeyboard(it)
                            it.clearFocus()
                        }
                        getCharactersByQuery(query ?: "")
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?) = false
            })
        }
    }

    private fun hideKeyboard(view: View?) {
        view?.let {
            val inputMethodManager: InputMethodManager = requireContext()
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun getSpanCount(orientation: Int?): Int {
        return when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }
    }

    private fun getCharacters() {
        binding.progressBarCharacList.visibility = View.VISIBLE
        viewModel.getCharacters().observe(viewLifecycleOwner, Observer { marvelCharacters ->
            when {
                marvelCharacters.isEmpty() -> showSnackBar(getString(R.string.empty_list_message))
                else -> onNewItems(marvelCharacters)
            }
        })
    }

    private fun showSnackBar(message: String) {
        //TODO:
    }

    private fun onNewItems(characters: List<MarvelCharacter>) {
        binding.progressBarCharacList.visibility = View.GONE
        if (charactersAdapter == null) {
            charactersAdapter = CharactersAdapter(
                context,
                characters.toMutableList(),
                ::openCharacterDetail,
                ::addToFavorites,
                ::deleteFromFavorites
            )
            favorites?.let { charactersAdapter?.setFavoriteList(it) }
            binding.rvCharacters.adapter = charactersAdapter
        } else {
            charactersAdapter?.addCharacters(characters.toMutableList())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                Intent(activity, FavoritesActivity::class.java).apply {
                    startActivityForResult(this, FAVORITE_REQUEST_CODE)
                }
                return true
            }
            R.id.action_search -> {
                binding.apply {
                    when (searchViewMain.visibility) {
                        View.VISIBLE -> {
                            searchViewMain.visibility = View.GONE
                            resetToHomeList()
                        }
                        View.GONE -> {
                            searchViewMain.isEnabled = true
                            searchViewMain.visibility = View.VISIBLE
                        }
                    }
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun resetToHomeList() {
        charactersAdapter?.clearList()
        viewModel.characters.value = emptyList()
        viewModel.setQuery("")
        viewModel.isLastPage = false
        binding.progressBarCharacList.visibility = View.VISIBLE
        charactersAdapter = null
        viewModel.getCharacters()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                FAVORITE_REQUEST_CODE -> {
                    fetchFavoriteList()
                }
                DETAIL_REQUEST_CODE -> {
                    updateFavoritesWithReturnedData(data)
                }
                else -> {
                    return
                }
            }
        }
    }

    private fun updateFavoritesWithReturnedData(data: Intent?) {
        val isFavoriteCharacter = data?.getBooleanExtra(
            IS_FAVORITE_CHARACTER_TAG_ARG,
            false
        )
        val character = data?.getParcelableExtra<MarvelCharacter>(
            CHARACTER_TAG_ARG
        )
        when (isFavoriteCharacter) {
            true -> character?.let { marvelCharacter ->
                charactersAdapter?.getFavorites()?.let { favorites ->
                    if (!favorites.contains(marvelCharacter.id)) {
                        addToFavorites(marvelCharacter)
                        charactersAdapter?.setFavorite(marvelCharacter)
                    }
                }
            }
            false -> character?.let { marvelCharacter ->
                charactersAdapter?.getFavorites()?.let { favorites ->
                    if (favorites.contains(marvelCharacter.id)) {
                        deleteFromFavorites(marvelCharacter)
                        charactersAdapter?.removeFavorite(marvelCharacter)
                    }
                }
            }
        }
    }

    private fun openCharacterDetail(character: MarvelCharacter) {
        val isFavoriteCharacter =
            charactersAdapter?.getFavorites()?.contains(character.id) ?: false
        val intent = Intent(context, DetailActivity::class.java)
        intent.apply {
            putExtra(CHARACTER_TAG_ARG, character)
            putExtra(IS_FAVORITE_CHARACTER_TAG_ARG, isFavoriteCharacter)
        }
        startActivityForResult(intent, DETAIL_REQUEST_CODE)
    }

    private fun addToFavorites(character: MarvelCharacter) {
        viewModel.saveCharacter(character)
    }

    private fun deleteFromFavorites(character: MarvelCharacter) {
        viewModel.deleteCharacter(character)
    }

    override fun onResume() {
        super.onResume()
        view?.let {
            hideKeyboard(it)
            it.clearFocus()
        }
    }
}