package com.raithavarta.app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.raithavarta.app.R
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.FragmentCategoriesBinding
import com.raithavarta.app.ui.home.HomeViewModelFactory

/**
 * Displays the 4 crop categories as large tappable cards.
 * Tapping a category navigates to filtered tip list.
 */
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardPaddy.setOnClickListener {
            navigateToCategory("paddy", getString(R.string.paddy))
        }
        binding.cardAreca.setOnClickListener {
            navigateToCategory("areca_nut", getString(R.string.areca_nut))
        }
        binding.cardCoconut.setOnClickListener {
            navigateToCategory("coconut", getString(R.string.coconut))
        }
        binding.cardTomato.setOnClickListener {
            navigateToCategory("tomato", getString(R.string.tomato))
        }
    }

    private fun navigateToCategory(category: String, displayName: String) {
        val bundle = Bundle().apply {
            putString("category", category)
            putString("categoryName", displayName)
        }
        findNavController().navigate(R.id.action_categories_to_categoryDetail, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
