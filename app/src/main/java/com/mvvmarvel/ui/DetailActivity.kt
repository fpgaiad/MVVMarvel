package com.mvvmarvel.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mvvmarvel.R
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var marvelCharacter: MarvelCharacter? = null
    private var isFavoriteCharacter = false

    companion object {
        const val IS_FAVORITE_CHARACTER_TAG_ARG = "is_favorite_character_tag_arg"
        const val CHARACTER_TAG_ARG = "character_tag_arg"
        const val IMAGE_SIZE = "/landscape_xlarge."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val flagLayoutNoLimits = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        window.setFlags(flagLayoutNoLimits, flagLayoutNoLimits)

        val toolbar = binding.toolbarDetail
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        marvelCharacter = intent.getParcelableExtra(CHARACTER_TAG_ARG)
        isFavoriteCharacter = intent
            .getBooleanExtra(IS_FAVORITE_CHARACTER_TAG_ARG, false)
        setFavoriteIcon()

        val imagePath = marvelCharacter?.thumbnail?.path?.replace("http", "https")
        val imageExtension = marvelCharacter?.thumbnail?.extension


        binding.apply {
            tvCharacterName.text = marvelCharacter?.name
            tvCharacterDescription.text = marvelCharacter?.description
            ivFavoriteDetail.setOnClickListener {
                toggleFavorite(it as ImageView)
            }

            Picasso.get()
                .load("$imagePath$IMAGE_SIZE$imageExtension")
                .placeholder(R.drawable.placeholder)
                .into(ivCharacterImageDetail)
        }
    }

    private fun setFavoriteIcon() {
        binding.ivFavoriteDetail.apply {
            if (isFavoriteCharacter) setImageResource(R.drawable.ic_favorite_full)
            else setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun toggleFavorite(view: ImageView) {
        isFavoriteCharacter = when (isFavoriteCharacter) {
            true -> {
                view.setImageResource(R.drawable.ic_favorite_border)
                false
            }
            false -> {
                view.setImageResource(R.drawable.ic_favorite_full)
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val resultIntent = intent
                resultIntent.putExtra(IS_FAVORITE_CHARACTER_TAG_ARG, isFavoriteCharacter)
                resultIntent.putExtra(CHARACTER_TAG_ARG, marvelCharacter)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}