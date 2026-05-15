package com.raithavarta.app.ui.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raithavarta.app.R
import com.raithavarta.app.data.model.SuccessStory
import com.raithavarta.app.databinding.ItemStoryCardBinding

class StoryAdapter : ListAdapter<SuccessStory, StoryAdapter.ViewHolder>(DiffCallback()) {

    var currentLanguage: String = "en"
        private set

    fun setLanguage(lang: String) {
        if (currentLanguage != lang) {
            currentLanguage = lang
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(
        private val binding: ItemStoryCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(story: SuccessStory) {
            val lang = currentLanguage
            val isKannada = lang == "kn"
            val isHindi = lang == "hi"

            binding.tvFarmerName.text = if (isKannada) story.farmerNameKn else if (isHindi) story.farmerNameHi else story.farmerNameEn
            binding.tvLocation.text = "📍 ${if (isKannada) story.locationKn else if (isHindi) story.locationHi else story.locationEn}"
            binding.tvStory.text = if (isKannada) story.storyKn else if (isHindi) story.storyHi else story.storyEn
            binding.tvYieldImprovement.text = "📈 ${story.yieldImprovement}" // yieldImprovement is mostly English specific for now, could be translated based on strings if needed later.

            val cropText = if (isKannada) {
                when (story.cropCategory) {
                    "paddy" -> "🌾 ಭತ್ತ"
                    "areca_nut" -> "🌴 ಅಡಿಕೆ"
                    "coconut" -> "🥥 ತೆಂಗು"
                    "tomato" -> "🍅 ಟೊಮ್ಯಾಟೊ"
                    else -> story.cropCategory
                }
            } else if (isHindi) {
                when (story.cropCategory) {
                    "paddy" -> "🌾 धान"
                    "areca_nut" -> "🌴 सुपारी"
                    "coconut" -> "🥥 नारियल"
                    "tomato" -> "🍅 टमाटर"
                    else -> story.cropCategory
                }
            } else {
                when (story.cropCategory) {
                    "paddy" -> "🌾 Paddy"
                    "areca_nut" -> "🌴 Areca Nut"
                    "coconut" -> "🥥 Coconut"
                    "tomato" -> "🍅 Tomato"
                    else -> story.cropCategory
                }
            }
            binding.chipCrop.text = cropText

            val imageRes = binding.root.context.resources.getIdentifier(
                story.imageResName, "drawable", binding.root.context.packageName
            )
            if (imageRes != 0) {
                binding.ivFarmer.setImageResource(imageRes)
            } else {
                binding.ivFarmer.setImageResource(R.drawable.placeholder_farmer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class DiffCallback : DiffUtil.ItemCallback<SuccessStory>() {
        override fun areItemsTheSame(a: SuccessStory, b: SuccessStory) = a.id == b.id
        override fun areContentsTheSame(a: SuccessStory, b: SuccessStory) = a == b
    }
}
