package com.raithavarta.app.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raithavarta.app.R
import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.databinding.ItemTipListBinding

/**
 * RecyclerView adapter for vertical tip list in category detail view.
 */
class CategoryTipAdapter(
    private val onBookmarkClick: (Tip) -> Unit,
    private val onShareClick: (Tip) -> Unit
) : ListAdapter<Tip, CategoryTipAdapter.ViewHolder>(DiffCallback()) {

    var currentLanguage: String = "en"
        private set

    fun setLanguage(lang: String) {
        if (currentLanguage != lang) {
            currentLanguage = lang
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(
        private val binding: ItemTipListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: Tip) {
            val lang = currentLanguage
            val isKannada = lang == "kn"
            val isHindi = lang == "hi"

            binding.tvTitle.text = if (isKannada) tip.titleKn else if (isHindi) tip.titleHi else tip.titleEn
            binding.tvDescription.text = if (isKannada) tip.descriptionKn else if (isHindi) tip.descriptionHi else tip.descriptionEn
            binding.tvAction.text = "✅ ${if (isKannada) tip.actionKn else if (isHindi) tip.actionHi else tip.actionEn}"
            
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
            binding.chipCategory.text = cropText

            val imageRes = binding.root.context.resources.getIdentifier(
                tip.imageResName, "drawable", binding.root.context.packageName
            )
            if (imageRes != 0) {
                binding.ivImage.setImageResource(imageRes)
            } else {
                binding.ivImage.setImageResource(R.drawable.placeholder_crop)
            }

            binding.btnBookmark.setIconResource(
                if (tip.isBookmarked) R.drawable.ic_bookmark_filled
                else R.drawable.ic_bookmark_outline
            )
            binding.btnBookmark.setOnClickListener { onBookmarkClick(tip) }
            binding.btnShare.setOnClickListener { onShareClick(tip) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTipListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<Tip>() {
        override fun areItemsTheSame(a: Tip, b: Tip) = a.id == b.id
        override fun areContentsTheSame(a: Tip, b: Tip) = a == b
    }
}
