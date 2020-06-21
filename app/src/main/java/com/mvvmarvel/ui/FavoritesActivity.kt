package com.mvvmarvel.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mvvmarvel.R

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        supportActionBar?.title = "Favorites"

        val charactersFragment = CharactersFragment.newInstance(true)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment_container_favorites, charactersFragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.favorites_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_home -> {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}