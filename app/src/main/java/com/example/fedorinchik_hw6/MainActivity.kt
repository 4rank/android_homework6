package com.example.fedorinchik_hw6

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


private lateinit var bottomNavigationView: BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val PREFER_SAVE = "save"
    var dbHelper: dbHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation()
        dbHelper
    }

    private fun bottomNavigation() {
        bottomNavigationView = findViewById(R.id.bot_nav_view_first)
        val navigationController = findNavController(R.id.mainFragment)
        bottomNavigationView.setupWithNavController(navigationController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
            this.getSharedPreferences(PREFER_SAVE, Context.MODE_PRIVATE)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
            this.getSharedPreferences(PREFER_SAVE, Context.MODE_PRIVATE)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
        return true
    }
}