package com.example.hearthstonecards // Ensure this matches your manifest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hearthstonecards.databinding.ActivityMainBinding
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen();

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup shared preferences for storing theme choice
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Load and apply saved theme
        if (sharedPreferences.getBoolean("dark_mode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Setup Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup Bottom Navigation
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        // Handle bottom navigation item selection
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_theme -> {
                    toggleTheme()
                    true // Consume the event
                }
                else -> {
                    // Navigate to the selected fragment
                    navController.navigate(item.itemId)
                    true // Consume the event
                }
            }
        }

        // Optionally, you can set the default fragment here if needed
        // navController.navigate(R.id.navigation_card_back) // Uncomment to set a default fragment
    }

    private fun toggleTheme() {
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("dark_mode", false).apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferences.edit().putBoolean("dark_mode", true).apply()
        }
    }
}
