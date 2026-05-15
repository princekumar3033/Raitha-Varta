package com.raithavarta.app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.FragmentCategoryDetailBinding
import com.raithavarta.app.ui.home.HomeViewModel
import com.raithavarta.app.ui.home.HomeViewModelFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * Shows tips filtered by a specific crop category.
 * Displays as a vertical scrollable list (not swipeable cards).
 */
class CategoryDetailFragment : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((requireActivity().application as RaithaVartaApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments?.getString("category") ?: return
        val categoryName = arguments?.getString("categoryName") ?: category

        binding.toolbar.title = categoryName
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val adapter = CategoryTipAdapter(
            onBookmarkClick = { tip -> viewModel.toggleBookmark(tip) },
            onShareClick = { tip ->
                // Share logic same as HomeFragment
            }
        )

        binding.recyclerTips.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTips.adapter = adapter

        viewModel.filterByCategory(category)
        
        val prefsManager = (requireActivity().application as RaithaVartaApp).preferencesManager
        viewLifecycleOwner.lifecycleScope.launch {
            prefsManager.language.collect { lang ->
                adapter.setLanguage(lang)
            }
        }

        viewModel.allTips.observe(viewLifecycleOwner) { tips ->
            if (tips.isNullOrEmpty()) {
                binding.recyclerTips.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            } else {
                binding.recyclerTips.visibility = View.VISIBLE
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
