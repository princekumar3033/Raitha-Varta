package com.raithavarta.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.raithavarta.app.MainActivity
import com.raithavarta.app.R
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Home screen — displays swipeable flash-card tips using ViewPager2.
 * Shows daily tip on launch. Supports bookmarking and sharing.
 */
class HomeFragment : Fragment(), MainActivity.OnReselectedListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory((requireActivity().application as RaithaVartaApp).repository)
    }
    private lateinit var tipAdapter: TipCardAdapter
    private lateinit var prefsManager: com.raithavarta.app.utils.PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = (requireActivity().application as RaithaVartaApp).preferencesManager

        setupViewPager()
        observeData()
        setupActions()
    }

    private fun setupViewPager() {
        tipAdapter = TipCardAdapter(
            onBookmarkClick = { tip ->
                viewModel.toggleBookmark(tip)
            },
            onShareClick = { tip ->
                shareTip(tip)
            },
            languageProvider = {
                val app = requireActivity().application as RaithaVartaApp
                // Simple synchronous check
                "en"
            }
        )
        binding.viewPagerTips.adapter = tipAdapter
        binding.viewPagerTips.offscreenPageLimit = 3

        // Disable overscroll clip for peek effect
        binding.viewPagerTips.clipToPadding = false
        binding.viewPagerTips.clipChildren = false
        (binding.viewPagerTips.getChildAt(0) as? RecyclerView)?.apply {
            clipToPadding = false
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        // Card peek + depth effect using CompositePageTransformer
        val compositeTransformer = CompositePageTransformer()
        compositeTransformer.addTransformer(MarginPageTransformer(40))
        compositeTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
            page.alpha = 0.5f + r * 0.5f
        }
        binding.viewPagerTips.setPageTransformer(compositeTransformer)

        // Save current position
        binding.viewPagerTips.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateCounter(position)
                lifecycleScope.launch {
                    prefsManager.setLastTipIndex(position)
                }
            }
        })
    }

    private fun observeData() {
        // Observe language changes and update adapter
        lifecycleScope.launch {
            prefsManager.language.collect { lang ->
                tipAdapter.setLanguage(lang)
            }
        }

        viewModel.allTips.observe(viewLifecycleOwner) { tips ->
            if (tips.isNullOrEmpty()) {
                binding.viewPagerTips.visibility = View.GONE
                binding.emptyState.visibility = View.VISIBLE
            } else {
                binding.viewPagerTips.visibility = View.VISIBLE
                binding.emptyState.visibility = View.GONE
                tipAdapter.submitList(tips)

                // Restore last viewed position
                lifecycleScope.launch {
                    val lastIndex = prefsManager.lastTipIndex.first()
                    if (lastIndex in tips.indices) {
                        binding.viewPagerTips.setCurrentItem(lastIndex, false)
                    }
                    updateCounter(binding.viewPagerTips.currentItem)
                }
            }
        }
    }

    private fun setupActions() {
        binding.chipAll.setOnClickListener { viewModel.filterByCategory(null) }
        binding.chipPaddy.setOnClickListener { viewModel.filterByCategory("paddy") }
        binding.chipAreca.setOnClickListener { viewModel.filterByCategory("areca_nut") }
        binding.chipCoconut.setOnClickListener { viewModel.filterByCategory("coconut") }
        binding.chipTomato.setOnClickListener { viewModel.filterByCategory("tomato") }
    }

    private fun updateCounter(position: Int) {
        val total = tipAdapter.itemCount
        if (total > 0) {
            binding.tvCounter.text = "${position + 1} / $total"
        }
    }

    private fun shareTip(tip: com.raithavarta.app.data.model.Tip) {
        val lang = tipAdapter.currentLanguage
        val title = if (lang == "kn") tip.titleKn else if (lang == "hi") tip.titleHi else tip.titleEn
        val desc = if (lang == "kn") tip.descriptionKn else if (lang == "hi") tip.descriptionHi else tip.descriptionEn
        val action = if (lang == "kn") tip.actionKn else if (lang == "hi") tip.actionHi else tip.actionEn

        val shareText = """
            🌾 *$title*
            
            $desc
            
            ✅ Action: $action
            
            — Shared from Raitha-Varta 🌱
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_tip)))
    }

    override fun onReselected() {
        binding.viewPagerTips.setCurrentItem(0, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
