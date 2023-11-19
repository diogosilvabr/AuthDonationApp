package com.donation.authdonationtest

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.Random

class TokenGeneratorActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var ampulhetaImageView: ImageView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var tokenTextView: TextView
    private var currentToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token_generator)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        ampulhetaImageView = findViewById(R.id.ampulheta)
        tokenTextView = findViewById(R.id.tokenText)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    logoutUser()
                    true
                }
                else -> false
            }
        }

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) 
            }

            override fun onFinish() {
                generateNewToken()
            }
        }.start()
        savedInstanceState?.getString("TOKEN")?.let {
            currentToken = it
            tokenTextView.text = it
        } ?: generateNewToken()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TOKEN", currentToken)
    }

    private fun generateNewToken() {
        val random = Random()
        currentToken = String.format("%06d", random.nextInt(999999))
        tokenTextView.text = currentToken
        countDownTimer.start()
    }

    private fun logoutUser() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}
