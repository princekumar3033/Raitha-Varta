package com.raithavarta.app.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.raithavarta.app.MainActivity
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Splash screen using the AndroidX SplashScreen API.
 * Routes to Onboarding (first launch) or MainActivity (returning user).
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Keep splash visible until we determine the route
        splashScreen.setKeepOnScreenCondition { keepSplash }

        lifecycleScope.launch {
            val app = application as RaithaVartaApp
            val onboardingDone = app.preferencesManager.onboardingDone.first()

            keepSplash = false

            val target = if (onboardingDone) {
                MainActivity::class.java
            } else {
                OnboardingActivity::class.java
            }

            startActivity(Intent(this@SplashActivity, target))
            finish()
        }
    }
}
