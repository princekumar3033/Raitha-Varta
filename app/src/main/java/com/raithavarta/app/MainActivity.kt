package com.raithavarta.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.raithavarta.app.databinding.ActivityMainBinding

/**
 * Main activity hosting the bottom navigation and NavHostFragment.
 * All main screens (Home, Categories, Expert Ask, Stories, Settings) are fragments.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // Permission result handled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        askNotificationPermission()
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        // Handle re-selection — scroll to top or reset state
        binding.bottomNavigation.setOnItemReselectedListener { item ->
            // Let the current fragment handle reselection if needed
            val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment
            if (currentFragment is OnReselectedListener) {
                currentFragment.onReselected()
            }
        }
    }

    /**
     * Interface for fragments to handle bottom nav re-selection.
     */
    interface OnReselectedListener {
        fun onReselected()
    }
}
