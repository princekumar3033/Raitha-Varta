package com.raithavarta.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single farming tip flash-card.
 * Each tip has bilingual content (English + Kannada) and belongs to a crop category.
 */
@Entity(tableName = "tips")
data class Tip(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titleEn: String,
    val titleKn: String,
    val titleHi: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val descriptionHi: String,
    val actionEn: String,        // e.g. "Spray neem oil on leaves"
    val actionKn: String,
    val actionHi: String,
    val cropCategory: String,    // "paddy", "areca_nut", "coconut", "tomato"
    val imageResName: String,    // drawable resource name
    val tipType: String,         // "pest", "fertilizer", "water", "harvest", "general"
    val season: String,          // "kharif", "rabi", "summer", "all"
    val isBookmarked: Boolean = false,
    val dateAdded: Long = System.currentTimeMillis(),
    val priority: Int = 0        // higher = shown first
)
