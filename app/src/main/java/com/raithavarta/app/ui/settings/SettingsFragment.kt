package com.raithavarta.app.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.raithavarta.app.R
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.FragmentSettingsBinding
import com.raithavarta.app.utils.PreferencesManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Settings screen — language toggle, notification preferences, about section.
 * Language changes trigger app-wide locale switch via AndroidX per-app language API.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefsManager: PreferencesManager
    private var suppressLanguageCallback = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = (requireActivity().application as RaithaVartaApp).preferencesManager

        setupLanguageToggle()
        setupNotificationToggle()
        setupAbout()
    }

    private fun setupLanguageToggle() {
        lifecycleScope.launch {
            val currentLang = prefsManager.language.first()
            suppressLanguageCallback = true
            binding.toggleLanguage.check(
                when (currentLang) {
                    "kn" -> R.id.btnKannada
                    "hi" -> R.id.btnHindi
                    else -> R.id.btnEnglish
                }
            )
            suppressLanguageCallback = false
        }

        binding.toggleLanguage.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked && !suppressLanguageCallback) {
                val lang = when (checkedId) {
                    R.id.btnKannada -> "kn"
                    R.id.btnHindi -> "hi"
                    else -> "en"
                }
                lifecycleScope.launch {
                    prefsManager.setLanguage(lang)
                    // Apply locale change using AndroidX per-app language API
                    val localeList = LocaleListCompat.forLanguageTags(lang)
                    AppCompatDelegate.setApplicationLocales(localeList)
                }
            }
        }
    }

    private fun setupNotificationToggle() {
        lifecycleScope.launch {
            val enabled = prefsManager.notificationsEnabled.first()
            binding.switchNotifications.isChecked = enabled
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                prefsManager.setNotificationsEnabled(isChecked)
            }
        }
    }

    private fun setupAbout() {
        binding.tvVersion.text = getString(R.string.version_format, "1.0.0")
        binding.tvAboutDesc.text = getString(R.string.about_description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
