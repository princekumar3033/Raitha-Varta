package com.raithavarta.app.ui.home

import androidx.lifecycle.*
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.data.repository.TipRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TipRepository) : ViewModel() {

    private val _category = MutableLiveData<String?>(null)

    // A daily seed based on the epoch day so it changes exactly once a day
    private val dailySeed = java.time.LocalDate.now().toEpochDay()

    val allTips: LiveData<List<Tip>> = _category.switchMap { cat ->
        val source = if (cat == null) {
            repository.getAllTips()
        } else {
            repository.getTipsByCategory(cat)
        }
        
        // Transform the source LiveData to shuffle it consistently based on the daily seed,
        // and take top 5 tips for the "Daily Tips" feed.
        source.map { tips ->
            if (tips.isEmpty()) return@map tips
            
            // Create a random instance seeded by dailySeed
            val random = java.util.Random(dailySeed)
            
            // Shuffle the list and take 5 tips
            val shuffledTips = tips.shuffled(random)
            shuffledTips.take(5)
        }
    }

    fun filterByCategory(category: String?) {
        _category.value = category
    }

    fun toggleBookmark(tip: Tip) {
        viewModelScope.launch {
            repository.toggleBookmark(tip.id, !tip.isBookmarked)
        }
    }
}

class HomeViewModelFactory(
    private val repository: TipRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
