package com.raithavarta.app.ui.stories

import androidx.lifecycle.*
import com.raithavarta.app.data.model.SuccessStory
import com.raithavarta.app.data.repository.TipRepository
import kotlinx.coroutines.launch

class StoriesViewModel(private val repository: TipRepository) : ViewModel() {
    val allStories: LiveData<List<SuccessStory>> = repository.getAllStories()

    fun addStory(story: SuccessStory) {
        viewModelScope.launch {
            repository.insertStory(story)
        }
    }
}

class StoriesViewModelFactory(
    private val repository: TipRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            return StoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
