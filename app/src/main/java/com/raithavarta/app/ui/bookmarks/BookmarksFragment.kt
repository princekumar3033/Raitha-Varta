package com.raithavarta.app.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.FragmentBookmarksBinding
import com.raithavarta.app.ui.categories.CategoryTipAdapter
import com.raithavarta.app.ui.home.HomeViewModel
import com.raithavarta.app.ui.home.HomeViewModelFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * Bookmarks screen — shows all saved/bookmarked tips.
 * Handles empty state when no tips are bookmarked.
 */
class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookmarksViewModel by viewModels {
        BookmarksViewModelFactory((requireActivity().application as RaithaVartaApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryTipAdapter(
            onBookmarkClick = { tip -> viewModel.toggleBookmark(tip.id, !tip.isBookmarked) },
            onShareClick = { /* Share logic */ }
        )

        binding.recyclerBookmarks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerBookmarks.adapter = adapter

        val prefsManager = (requireActivity().application as RaithaVartaApp).preferencesManager
        viewLifecycleOwner.lifecycleScope.launch {
            prefsManager.language.collect { lang ->
                adapter.setLanguage(lang)
            }
        }

        viewModel.bookmarkedTips.observe(viewLifecycleOwner) { tips ->
            if (tips.isNullOrEmpty()) {
                binding.recyclerBookmarks.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            } else {
                binding.recyclerBookmarks.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
                adapter.submitList(tips)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
