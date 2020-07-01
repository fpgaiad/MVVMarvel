package com.mvvmarvel.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mvvmarvel.R
import com.mvvmarvel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.title = getString(R.string.marvel)

        binding.searchViewMain.visibility = View.GONE

        val charactersFragment = CharactersFragment.newInstance(false)
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragmentContainerMain.id, charactersFragment)
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
            binding.apply {
                when (searchViewMain.visibility) {
                    View.VISIBLE -> searchViewMain.visibility = View.GONE
                    View.GONE -> searchViewMain.visibility = View.VISIBLE
                }
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}