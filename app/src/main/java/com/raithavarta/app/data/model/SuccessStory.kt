package com.raithavarta.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a success story from a local farmer who used a tip and got results.
 */
@Entity(tableName = "success_stories")
data class SuccessStory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val farmerNameEn: String,
    val farmerNameKn: String,
    val farmerNameHi: String,
    val locationEn: String,
    val locationKn: String,
    val locationHi: String,
    val storyEn: String,
    val storyKn: String,
    val storyHi: String,
    val cropCategory: String,
    val yieldImprovement: String,  // e.g. "30% increase"
    val imageResName: String,
    val tipId: Long = 0            // linked tip that helped
)
