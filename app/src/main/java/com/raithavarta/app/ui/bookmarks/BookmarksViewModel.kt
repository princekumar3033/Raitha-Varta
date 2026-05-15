package com.raithavarta.app.ui.bookmarks

import androidx.lifecycle.*
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.data.repository.TipRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(private val repository: TipRepository) : ViewModel() {
    val bookmarkedTips: LiveData<List<Tip>> = repository.getBookmarkedTips()

    fun toggleBookmark(tipId: Long, bookmarked: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmark(tipId, bookmarked)
        }
    }
}

class BookmarksViewModelFactory(
    private val repository: TipRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarksViewModel::class.java)) {
            return BookmarksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
