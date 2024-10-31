package com.example.hearthstonecards // Ensure this matches your manifest

import CardBackFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hearthstonecards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup Bottom Navigation
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        // Optionally, you can set the default fragment here if needed
        // navController.navigate(R.id.navigation_card_back) // Navigate to CardBackFragment by default
    }
}
