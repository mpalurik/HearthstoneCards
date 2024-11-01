package com.example.hearthstonecards  // Change to your package name

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Navigate to MainActivity after a delay
        Handler().postDelayed({
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Close the SplashActivity
        }, 4000) // Duration of the splash screen in milliseconds
    }
}
