package com.raithavarta.app.data.model

/**
 * Represents an expert analysis result for a diseased leaf photo.
 * Used in the Expert Ask feature (simulated AI response).
 */
data class ExpertAnalysis(
    val diseaseNameEn: String,
    val diseaseNameKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val remedyEn: String,
    val remedyKn: String,
    val severity: String,         // "low", "medium", "high"
    val confidence: Float,        // 0.0 to 1.0
    val preventionEn: String,
    val preventionKn: String
)
