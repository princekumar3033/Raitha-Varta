package com.raithavarta.app.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.raithavarta.app.data.local.AppDatabase
import com.raithavarta.app.data.local.TipDao
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.data.model.SuccessStory
import com.raithavarta.app.data.seed.SeedData

/**
 * Single source of truth for all tip and story data.
 * Handles seeding on first launch and all CRUD operations.
 */
class TipRepository(context: Context) {

    private val dao: TipDao = AppDatabase.getInstance(context).tipDao()

    // ── Tips ──────────────────────────────────────────────

    fun getAllTips(): LiveData<List<Tip>> = dao.getAllTips()

    suspend fun getAllTipsList(): List<Tip> = dao.getAllTipsList()

    fun getTipsByCategory(category: String): LiveData<List<Tip>> =
        dao.getTipsByCategory(category)

    suspend fun getTipsByCategoryList(category: String): List<Tip> =
        dao.getTipsByCategoryList(category)

    fun getBookmarkedTips(): LiveData<List<Tip>> = dao.getBookmarkedTips()

    fun getTipsByType(type: String): LiveData<List<Tip>> = dao.getTipsByType(type)

    fun getTipsBySeason(season: String): LiveData<List<Tip>> = dao.getTipsBySeason(season)

    fun searchTips(query: String): LiveData<List<Tip>> = dao.searchTips(query)

    suspend fun getTipById(id: Long): Tip? = dao.getTipById(id)

    suspend fun toggleBookmark(tipId: Long, bookmarked: Boolean) {
        dao.setBookmarked(tipId, bookmarked)
    }

    // ── Stories ───────────────────────────────────────────

    fun getAllStories(): LiveData<List<SuccessStory>> = dao.getAllStories()

    fun getStoriesByCategory(category: String): LiveData<List<SuccessStory>> =
        dao.getStoriesByCategory(category)

    suspend fun insertStory(story: SuccessStory): Long = dao.insertStory(story)

    // ── Seeding ───────────────────────────────────────────

    suspend fun seedIfEmpty() {
        if (dao.getTipCount() == 0) {
            dao.insertAllTips(SeedData.tips)
        }
        if (dao.getStoryCount() == 0) {
            dao.insertAllStories(SeedData.stories)
        }
    }
}
