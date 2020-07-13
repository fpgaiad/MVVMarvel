package com.mvvmarvel.ui

import android.os.Bundle
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

        val charactersFragment = CharactersFragment.newInstance(false)
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragmentContainerMain.id, charactersFragment)
            commit()
        }
    }
}