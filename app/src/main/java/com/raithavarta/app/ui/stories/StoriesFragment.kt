package com.raithavarta.app.ui.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raithavarta.app.R
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.data.model.SuccessStory
import com.raithavarta.app.databinding.FragmentStoriesBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * Success Stories screen — shows farmer success stories in a scrollable list.
 * Includes FAB to add a new story via dialog.
 */
class StoriesFragment : Fragment() {

    private var _binding: FragmentStoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoriesViewModel by viewModels {
        StoriesViewModelFactory((requireActivity().application as RaithaVartaApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StoryAdapter()
        binding.recyclerStories.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerStories.adapter = adapter

        val prefsManager = (requireActivity().application as RaithaVartaApp).preferencesManager
        viewLifecycleOwner.lifecycleScope.launch {
            prefsManager.language.collect { lang ->
                adapter.setLanguage(lang)
            }
        }

        viewModel.allStories.observe(viewLifecycleOwner) { stories ->
            if (stories.isNullOrEmpty()) {
                binding.recyclerStories.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            } else {
                binding.recyclerStories.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
                adapter.submitList(stories)
            }
        }

        // Add Story FAB
        binding.fabAddStory.setOnClickListener {
            showAddStoryDialog()
        }
    }

    private fun showAddStoryDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_story, null)

        val etName = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etFarmerName)
        val etLocation = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etLocation)
        val etStory = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etStory)
        val etYield = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etYieldImprovement)
        val chipGroup = dialogView.findViewById<com.google.android.material.chip.ChipGroup>(R.id.chipGroupCrop)

        // Default selection
        chipGroup.check(R.id.chipPaddyStory)

        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogView)
            .setPositiveButton(R.string.submit_story) { _, _ ->
                val name = etName.text?.toString()?.trim() ?: ""
                val location = etLocation.text?.toString()?.trim() ?: ""
                val story = etStory.text?.toString()?.trim() ?: ""
                val yield = etYield.text?.toString()?.trim() ?: ""

                if (name.isEmpty() || location.isEmpty() || story.isEmpty() || yield.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val cropCategory = when (chipGroup.checkedChipId) {
                    R.id.chipPaddyStory -> "paddy"
                    R.id.chipArecaStory -> "areca_nut"
                    R.id.chipCoconutStory -> "coconut"
                    R.id.chipTomatoStory -> "tomato"
                    else -> "paddy"
                }

                val newStory = SuccessStory(
                    farmerNameEn = name,
                    farmerNameKn = name,  // User enters their own name
                    farmerNameHi = name,
                    locationEn = location,
                    locationKn = location,
                    locationHi = location,
                    storyEn = story,
                    storyKn = story,
                    storyHi = story,
                    cropCategory = cropCategory,
                    yieldImprovement = yield,
                    imageResName = "placeholder_farmer"
                )

                viewModel.addStory(newStory)
                Toast.makeText(requireContext(), R.string.story_submitted, Toast.LENGTH_LONG).show()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
