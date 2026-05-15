package com.raithavarta.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raithavarta.app.R
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.databinding.ItemTipCardBinding

/**
 * ViewPager2 adapter for tip flash-cards.
 * Supports bilingual content via language toggle.
 */
class TipCardAdapter(
    private val onBookmarkClick: (Tip) -> Unit,
    private val onShareClick: (Tip) -> Unit,
    private val languageProvider: () -> String = { "en" }
) : ListAdapter<Tip, TipCardAdapter.TipViewHolder>(TipDiffCallback()) {

    var currentLanguage: String = "en"
        private set

    fun setLanguage(lang: String) {
        if (currentLanguage != lang) {
            currentLanguage = lang
            notifyDataSetChanged()
        }
    }

    inner class TipViewHolder(
        private val binding: ItemTipCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: Tip) {
            val isKannada = currentLanguage == "kn"
            val isHindi = currentLanguage == "hi"

            // Set content based on language
            binding.tvTipTitle.text = if (isKannada) tip.titleKn else if (isHindi) tip.titleHi else tip.titleEn
            binding.tvTipDescription.text = if (isKannada) tip.descriptionKn else if (isHindi) tip.descriptionHi else tip.descriptionEn
            binding.tvTipAction.text = if (isKannada) tip.actionKn else if (isHindi) tip.actionHi else tip.actionEn

            // Crop category badge
            val cropText = if (isKannada) {
                when (tip.cropCategory) {
                    "paddy" -> "🌾 ಭತ್ತ"
                    "areca_nut" -> "🌴 ಅಡಿಕೆ"
                    "coconut" -> "🥥 ತೆಂಗು"
                    "tomato" -> "🍅 ಟೊಮ್ಯಾಟೊ"
                    else -> tip.cropCategory
                }
            } else if (isHindi) {
                when (tip.cropCategory) {
                    "paddy" -> "🌾 धान"
                    "areca_nut" -> "🌴 सुपारी"
                    "coconut" -> "🥥 नारियल"
                    "tomato" -> "🍅 टमाटर"
                    else -> tip.cropCategory
                }
            } else {
                when (tip.cropCategory) {
                    "paddy" -> "🌾 Paddy"
                    "areca_nut" -> "🌴 Areca Nut"
                    "coconut" -> "🥥 Coconut"
                    "tomato" -> "🍅 Tomato"
                    else -> tip.cropCategory
                }
            }
            binding.chipCropCategory.text = cropText

            // Tip type icon
            binding.ivTipTypeIcon.setImageResource(
                when (tip.tipType) {
                    "pest" -> R.drawable.ic_pest
                    "fertilizer" -> R.drawable.ic_fertilizer
                    "water" -> R.drawable.ic_water
                    "harvest" -> R.drawable.ic_harvest
                    else -> R.drawable.ic_general_tip
                }
            )

            // Tip image
            val imageRes = binding.root.context.resources.getIdentifier(
                tip.imageResName, "drawable", binding.root.context.packageName
            )
            if (imageRes != 0) {
                binding.ivTipImage.setImageResource(imageRes)
            } else {
                binding.ivTipImage.setImageResource(R.drawable.placeholder_crop)
            }

            // Bookmark state
            updateBookmarkIcon(tip.isBookmarked)
            binding.btnBookmark.setOnClickListener {
                onBookmarkClick(tip)
            }

            // Share
            binding.btnShare.setOnClickListener {
                onShareClick(tip)
            }

            // Season tag
            binding.tvSeason.text = if (isKannada) {
                when (tip.season) {
                    "kharif" -> "ಖಾರಿಫ್ ಋತು"
                    "rabi" -> "ರಬಿ ಋತು"
                    "summer" -> "ಬೇಸಿಗೆ"
                    else -> "ಎಲ್ಲಾ ಋತು"
                }
            } else if (isHindi) {
                when (tip.season) {
                    "kharif" -> "खरीफ मौसम"
                    "rabi" -> "रबी मौसम"
                    "summer" -> "गर्मी"
                    else -> "सभी मौसम"
                }
            } else {
                when (tip.season) {
                    "kharif" -> "Kharif Season"
                    "rabi" -> "Rabi Season"
                    "summer" -> "Summer"
                    else -> "All Seasons"
                }
            }
        }

        private fun updateBookmarkIcon(isBookmarked: Boolean) {
            binding.btnBookmark.setIconResource(
                if (isBookmarked) R.drawable.ic_bookmark_filled
                else R.drawable.ic_bookmark_outline
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemTipCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TipDiffCallback : DiffUtil.ItemCallback<Tip>() {
        override fun areItemsTheSame(oldItem: Tip, newItem: Tip) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Tip, newItem: Tip) = oldItem == newItem
    }
}
