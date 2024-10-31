package com.example.hearthstonecards // Ensure this matches your manifest

import CardBackFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hearthstonecards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default fragment load (CardBack page initially)
        loadFragment(CardBackFragment())

        // Setup Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_card_back -> {
                    loadFragment(CardBackFragment())
                    true
                }
                // Add more cases for other fragments as needed
                else -> false
            }
        }
    }

    // Function to switch fragments
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
