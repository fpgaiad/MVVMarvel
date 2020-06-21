package com.mvvmarvel.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.mvvmarvel.R

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Marvel"

        searchView = findViewById(R.id.sv_main)
        searchView.visibility = View.GONE

        val charactersFragment = CharactersFragment.newInstance(false)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment_container_main, charactersFragment)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            Intent(this, FavoritesActivity::class.java).apply {
                startActivity(this)
            }
            true
        }
        R.id.action_search -> {
            when (searchView.visibility) {
                View.VISIBLE -> searchView.visibility = View.GONE
                View.GONE -> {
                    searchView.apply {
                        visibility = View.VISIBLE
                    }
                }
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}