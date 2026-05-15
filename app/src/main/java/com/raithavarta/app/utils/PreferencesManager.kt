package com.raithavarta.app.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Manages app language (English / Kannada) via DataStore.
 * Also tracks onboarding completion and other preferences.
 */
class PreferencesManager(private val context: Context) {

    companion object {
        val LANGUAGE_KEY = stringPreferencesKey("language")          // "en" or "kn"
        val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val LAST_TIP_INDEX = intPreferencesKey("last_tip_index")
    }

    // ── Language ─────────────────────────────────────────

    val language: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[LANGUAGE_KEY] ?: "en"
    }

    suspend fun setLanguage(lang: String) {
        context.dataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = lang
        }
    }

    // ── Onboarding ───────────────────────────────────────

    val onboardingDone: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ONBOARDING_DONE] ?: false
    }

    suspend fun setOnboardingDone() {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_DONE] = true
        }
    }

    // ── Notifications ────────────────────────────────────

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[NOTIFICATIONS_ENABLED] ?: true
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[NOTIFICATIONS_ENABLED] = enabled
        }
    }

    // ── Last viewed tip index ────────────────────────────

    val lastTipIndex: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[LAST_TIP_INDEX] ?: 0
    }

    suspend fun setLastTipIndex(index: Int) {
        context.dataStore.edit { prefs ->
            prefs[LAST_TIP_INDEX] = index
        }
    }
}
