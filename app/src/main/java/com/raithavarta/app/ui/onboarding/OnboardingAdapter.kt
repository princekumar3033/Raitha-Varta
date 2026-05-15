package com.raithavarta.app.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raithavarta.app.databinding.ItemOnboardingSlideBinding

class OnboardingAdapter(
    private val slides: List<OnboardingActivity.OnboardingSlide>
) : RecyclerView.Adapter<OnboardingAdapter.SlideViewHolder>() {

    inner class SlideViewHolder(
        private val binding: ItemOnboardingSlideBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(slide: OnboardingActivity.OnboardingSlide) {
            binding.ivIcon.setImageResource(slide.iconRes)
            binding.tvTitle.text = slide.titleEn
            binding.tvDescription.text = slide.descEn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val binding = ItemOnboardingSlideBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SlideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(slides[position])
    }

    override fun getItemCount() = slides.size
}
