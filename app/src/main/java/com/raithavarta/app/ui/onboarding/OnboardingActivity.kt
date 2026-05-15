package com.raithavarta.app.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.raithavarta.app.MainActivity
import com.raithavarta.app.R
import com.raithavarta.app.RaithaVartaApp
import com.raithavarta.app.databinding.ActivityOnboardingBinding
import kotlinx.coroutines.launch

/**
 * 3-slide onboarding introducing the app's key features.
 * Shown only on first launch. Users can skip or complete all slides.
 */
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    data class OnboardingSlide(
        val titleEn: String,
        val titleKn: String,
        val descEn: String,
        val descKn: String,
        val iconRes: Int
    )

    private val slides = listOf(
        OnboardingSlide(
            "Daily Flash Tips",
            "ದೈನಂದಿನ ಫ್ಲ್ಯಾಶ್ ಸಲಹೆ",
            "Swipe through expert farming tips — one card at a time. Simple, visual, and actionable.",
            "ಒಂದೊಂದೇ ಕಾರ್ಡ್ ಮೂಲಕ ತಜ್ಞ ಕೃಷಿ ಸಲಹೆ ಸ್ವೈಪ್ ಮಾಡಿ. ಸರಳ ಮತ್ತು ಕ್ರಿಯಾತ್ಮಕ.",
            R.drawable.ic_onboarding_tips
        ),
        OnboardingSlide(
            "Filter by Your Crop",
            "ನಿಮ್ಮ ಬೆಳೆ ಮೂಲಕ ಫಿಲ್ಟರ್",
            "Get tips specific to Paddy, Areca Nut, Coconut, or Tomato. Your crop, your advice.",
            "ಭತ್ತ, ಅಡಿಕೆ, ತೆಂಗಿನಕಾಯಿ, ಅಥವಾ ಟೊಮ್ಯಾಟೊಗೆ ನಿರ್ದಿಷ್ಟ ಸಲಹೆ ಪಡೆಯಿರಿ.",
            R.drawable.ic_onboarding_filter
        ),
        OnboardingSlide(
            "Ask the Expert",
            "ತಜ್ಞರನ್ನು ಕೇಳಿ",
            "Snap a photo of a diseased leaf and get instant AI-powered advice. Works offline too!",
            "ರೋಗಗ್ರಸ್ತ ಎಲೆಯ ಫೋಟೊ ತೆಗೆದು AI ಸಲಹೆ ಪಡೆಯಿರಿ. ಆಫ್‌ಲೈನ್ ಕೂಡ ಕೆಲಸ ಮಾಡುತ್ತದೆ!",
            R.drawable.ic_onboarding_expert
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupButtons()
    }

    private fun setupViewPager() {
        val adapter = OnboardingAdapter(slides)
        binding.viewPager.adapter = adapter

        // Update dot indicators on page change
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                updateButtons(position)
            }
        })

        updateIndicators(0)
    }

    private fun setupButtons() {
        binding.btnSkip.setOnClickListener {
            finishOnboarding()
        }

        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < slides.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }
    }

    private fun updateButtons(position: Int) {
        if (position == slides.size - 1) {
            binding.btnNext.text = getString(R.string.get_started)
            binding.btnSkip.visibility = android.view.View.INVISIBLE
        } else {
            binding.btnNext.text = getString(R.string.next)
            binding.btnSkip.visibility = android.view.View.VISIBLE
        }
    }

    private fun updateIndicators(position: Int) {
        val indicators = listOf(binding.dot1, binding.dot2, binding.dot3)
        indicators.forEachIndexed { index, view ->
            if (index == position) {
                view.setBackgroundResource(R.drawable.dot_active)
            } else {
                view.setBackgroundResource(R.drawable.dot_inactive)
            }
        }
    }

    private fun finishOnboarding() {
        lifecycleScope.launch {
            (application as RaithaVartaApp).preferencesManager.setOnboardingDone()
            startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
            finish()
        }
    }
}
