package com.raithavarta.app.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.data.model.SuccessStory

@Dao
interface TipDao {

    // ── Tips ──────────────────────────────────────────────

    @Query("SELECT * FROM tips ORDER BY priority DESC, dateAdded DESC")
    fun getAllTips(): LiveData<List<Tip>>

    @Query("SELECT * FROM tips ORDER BY priority DESC, dateAdded DESC")
    suspend fun getAllTipsList(): List<Tip>

    @Query("SELECT * FROM tips WHERE cropCategory = :category ORDER BY priority DESC")
    fun getTipsByCategory(category: String): LiveData<List<Tip>>

    @Query("SELECT * FROM tips WHERE cropCategory = :category ORDER BY priority DESC")
    suspend fun getTipsByCategoryList(category: String): List<Tip>

    @Query("SELECT * FROM tips WHERE isBookmarked = 1 ORDER BY dateAdded DESC")
    fun getBookmarkedTips(): LiveData<List<Tip>>

    @Query("SELECT * FROM tips WHERE id = :tipId")
    suspend fun getTipById(tipId: Long): Tip?

    @Query("SELECT * FROM tips WHERE tipType = :type ORDER BY priority DESC")
    fun getTipsByType(type: String): LiveData<List<Tip>>

    @Query("SELECT * FROM tips WHERE season = :season OR season = 'all' ORDER BY priority DESC")
    fun getTipsBySeason(season: String): LiveData<List<Tip>>

    @Query("SELECT * FROM tips WHERE titleEn LIKE '%' || :query || '%' OR descriptionEn LIKE '%' || :query || '%' OR titleKn LIKE '%' || :query || '%'")
    fun searchTips(query: String): LiveData<List<Tip>>

    @Update
    suspend fun updateTip(tip: Tip)

    @Query("UPDATE tips SET isBookmarked = :bookmarked WHERE id = :tipId")
    suspend fun setBookmarked(tipId: Long, bookmarked: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTip(tip: Tip): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTips(tips: List<Tip>)

    @Delete
    suspend fun deleteTip(tip: Tip)

    @Query("SELECT COUNT(*) FROM tips")
    suspend fun getTipCount(): Int

    // ── Success Stories ───────────────────────────────────

    @Query("SELECT * FROM success_stories ORDER BY id DESC")
    fun getAllStories(): LiveData<List<SuccessStory>>

    @Query("SELECT * FROM success_stories WHERE cropCategory = :category")
    fun getStoriesByCategory(category: String): LiveData<List<SuccessStory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStories(stories: List<SuccessStory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: SuccessStory): Long

    @Query("SELECT COUNT(*) FROM success_stories")
    suspend fun getStoryCount(): Int
}
