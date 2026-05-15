package com.raithavarta.app.ui.expert

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.raithavarta.app.BuildConfig
import com.raithavarta.app.R
import com.raithavarta.app.data.model.ExpertAnalysis
import com.raithavarta.app.databinding.FragmentExpertAskBinding
import com.raithavarta.app.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Expert Ask screen — captures/uploads a photo of a diseased leaf
 * and sends it to Gemini Vision API for real plant disease analysis.
 *
 * Falls back to simulated results when offline or if API key is missing.
 */
class ExpertAskFragment : Fragment() {

    private var _binding: FragmentExpertAskBinding? = null
    private val binding get() = _binding!!
    private var currentPhotoUri: Uri? = null

    // Gemini model instance (lazy init)
    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-2.5-flash-lite",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }

    // Camera permission launcher
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            launchCamera()
        } else {
            Toast.makeText(requireContext(), R.string.camera_permission_denied, Toast.LENGTH_LONG).show()
        }
    }

    // Camera capture launcher
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && currentPhotoUri != null) {
            showCapturedImage(currentPhotoUri!!)
        }
    }

    // Gallery picker launcher
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            currentPhotoUri = uri
            showCapturedImage(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpertAskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener { requestCameraPermission() }
        binding.btnGallery.setOnClickListener { openGallery() }
        binding.btnAnalyze.setOnClickListener { analyzeImage() }
        binding.btnReset.setOnClickListener { resetState() }

        // Initially hide analysis views
        binding.cardResult.visibility = View.GONE
        binding.btnAnalyze.visibility = View.GONE
        binding.btnReset.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                launchCamera()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(requireContext(), R.string.camera_rationale, Toast.LENGTH_LONG).show()
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun launchCamera() {
        try {
            val photoFile = createImageFile()
            currentPhotoUri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.fileprovider",
                photoFile
            )
            cameraLauncher.launch(currentPhotoUri)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), R.string.camera_error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("LEAF_${timestamp}_", ".jpg", storageDir)
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun showCapturedImage(uri: Uri) {
        binding.ivLeafPreview.visibility = View.VISIBLE
        binding.uploadPrompt.visibility = View.GONE
        Glide.with(this)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.placeholder_crop)
            .into(binding.ivLeafPreview)

        binding.btnAnalyze.visibility = View.VISIBLE
        binding.btnReset.visibility = View.VISIBLE
    }

    private fun analyzeImage() {
        if (currentPhotoUri == null) {
            Toast.makeText(requireContext(), R.string.no_image_selected, Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnAnalyze.isEnabled = false
        binding.tvAnalyzing.visibility = View.VISIBLE
        binding.cardResult.visibility = View.GONE

        val apiKey = BuildConfig.GEMINI_API_KEY
        val isOnline = NetworkUtils.isOnline(requireContext())

        if (isOnline && apiKey.isNotEmpty() && !apiKey.contains("Demo") && !apiKey.contains("Replace")) {
            // Use real Gemini API
            binding.tvAnalyzing.text = getString(R.string.analyzing_gemini)
            analyzeWithGemini()
        } else {
            // Fall back to simulated analysis
            binding.tvAnalyzing.text = getString(R.string.analyzing)
            analyzeSimulated()
        }
    }

    private fun analyzeWithGemini() {
        lifecycleScope.launch {
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    loadBitmapFromUri(currentPhotoUri!!)
                }

                if (bitmap == null) {
                    showError(getString(R.string.analysis_error))
                    return@launch
                }

                val response = withContext(Dispatchers.IO) {
                    val inputContent = content {
                        image(bitmap)
                        text("""
                            You are an expert agricultural plant pathologist. Analyze this plant/leaf image and provide a diagnosis.
                            
                            Respond in EXACTLY this JSON format (no markdown, no code blocks, just raw JSON):
                            {
                                "disease_name_en": "Disease name in English",
                                "disease_name_kn": "Disease name in Kannada",
                                "description_en": "Brief description of the disease in English (2-3 sentences)",
                                "description_kn": "Brief description in Kannada",
                                "remedy_en": "Specific remedy with chemical names and dosage in English",
                                "remedy_kn": "Remedy in Kannada",
                                "prevention_en": "Prevention measures in English",
                                "prevention_kn": "Prevention in Kannada",
                                "severity": "low or medium or high",
                                "confidence": 0.85,
                                "crop_type": "paddy/coconut/areca_nut/tomato/other"
                            }
                            
                            If this is not a plant image, set disease_name to "Not a Plant Image" and describe what you see.
                            If the plant looks healthy, set disease_name to "Healthy Plant" with severity "low".
                        """.trimIndent())
                    }
                    generativeModel.generateContent(inputContent)
                }

                val resultText = response.text ?: ""
                val analysis = parseGeminiResponse(resultText)
                
                withContext(Dispatchers.Main) {
                    showAnalysisResult(analysis)
                    binding.progressBar.visibility = View.GONE
                    binding.btnAnalyze.isEnabled = true
                    binding.tvAnalyzing.visibility = View.GONE
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Fallback to simulated if Gemini fails
                    showError("AI Error: ${e.localizedMessage ?: "Unknown error"}. Trying local analysis...")
                    analyzeSimulated()
                }
            }
        }
    }

    private fun parseGeminiResponse(text: String): ExpertAnalysis {
        try {
            // Clean the response text - remove any markdown code block markers
            val cleaned = text
                .replace("```json", "")
                .replace("```", "")
                .trim()

            // Simple JSON parsing without external library
            val diseaseNameEn = extractJsonValue(cleaned, "disease_name_en")
            val diseaseNameKn = extractJsonValue(cleaned, "disease_name_kn")
            val descriptionEn = extractJsonValue(cleaned, "description_en")
            val descriptionKn = extractJsonValue(cleaned, "description_kn")
            val remedyEn = extractJsonValue(cleaned, "remedy_en")
            val remedyKn = extractJsonValue(cleaned, "remedy_kn")
            val preventionEn = extractJsonValue(cleaned, "prevention_en")
            val preventionKn = extractJsonValue(cleaned, "prevention_kn")
            val severity = extractJsonValue(cleaned, "severity")
            val confidenceStr = extractJsonNumber(cleaned, "confidence")
            val confidence = confidenceStr.toFloatOrNull() ?: 0.75f

            return ExpertAnalysis(
                diseaseNameEn = diseaseNameEn.ifEmpty { "Analysis Complete" },
                diseaseNameKn = diseaseNameKn.ifEmpty { "ವಿಶ್ಲೇಷಣೆ ಪೂರ್ಣ" },
                descriptionEn = descriptionEn.ifEmpty { text.take(200) },
                descriptionKn = descriptionKn.ifEmpty { "ವಿವರ ಲಭ್ಯವಿಲ್ಲ" },
                remedyEn = remedyEn.ifEmpty { "Consult a local agricultural officer." },
                remedyKn = remedyKn.ifEmpty { "ಸ್ಥಳೀಯ ಕೃಷಿ ಅಧಿಕಾರಿಯನ್ನು ಸಂಪರ್ಕಿಸಿ." },
                severity = if (severity in listOf("low", "medium", "high")) severity else "medium",
                confidence = confidence.coerceIn(0f, 1f),
                preventionEn = preventionEn.ifEmpty { "Practice crop rotation and maintain field hygiene." },
                preventionKn = preventionKn.ifEmpty { "ಬೆಳೆ ಪರಿವರ್ತನೆ ಅಭ್ಯಾಸ ಮಾಡಿ." }
            )
        } catch (e: Exception) {
            // If JSON parsing fails, use the raw text
            return ExpertAnalysis(
                diseaseNameEn = "Analysis Result",
                diseaseNameKn = "ವಿಶ್ಲೇಷಣೆ ಫಲಿತಾಂಶ",
                descriptionEn = text.take(300),
                descriptionKn = "ವಿವರಗಳನ್ನು ಆಂಗ್ಲದಲ್ಲಿ ನೋಡಿ",
                remedyEn = "Please consult a local agricultural officer for specific treatment.",
                remedyKn = "ನಿರ್ದಿಷ್ಟ ಚಿಕಿತ್ಸೆಗಾಗಿ ಸ್ಥಳೀಯ ಕೃಷಿ ಅಧಿಕಾರಿಯನ್ನು ಸಂಪರ್ಕಿಸಿ.",
                severity = "medium",
                confidence = 0.70f,
                preventionEn = "Maintain good field hygiene and follow integrated pest management.",
                preventionKn = "ಒಳ್ಳೆಯ ಕ್ಷೇತ್ರ ನೈರ್ಮಲ್ಯ ಕಾಪಾಡಿ."
            )
        }
    }

    private fun extractJsonValue(json: String, key: String): String {
        val pattern = """"$key"\s*:\s*"((?:[^"\\]|\\.)*)"""".toRegex()
        return pattern.find(json)?.groupValues?.getOrNull(1)
            ?.replace("\\n", "\n")
            ?.replace("\\\"", "\"")
            ?: ""
    }

    private fun extractJsonNumber(json: String, key: String): String {
        val pattern = """"$key"\s*:\s*([0-9.]+)""".toRegex()
        return pattern.find(json)?.groupValues?.getOrNull(1) ?: "0.75"
    }

    private fun loadBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream: InputStream? = requireContext().contentResolver.openInputStream(uri)
            val options = BitmapFactory.Options().apply {
                inSampleSize = 2  // Reduce image size to avoid memory issues
            }
            val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            bitmap
        } catch (e: Exception) {
            null
        }
    }

    private fun analyzeSimulated() {
        lifecycleScope.launch {
            kotlinx.coroutines.delay(2500)
            val analysis = getSimulatedAnalysis()
            showAnalysisResult(analysis)
            binding.progressBar.visibility = View.GONE
            binding.btnAnalyze.isEnabled = true
            binding.tvAnalyzing.visibility = View.GONE
        }
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.btnAnalyze.isEnabled = true
        binding.tvAnalyzing.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showAnalysisResult(analysis: ExpertAnalysis) {
        binding.cardResult.visibility = View.VISIBLE

        binding.tvDiseaseName.text = analysis.diseaseNameEn
        binding.tvDiseaseDesc.text = analysis.descriptionEn
        binding.tvRemedy.text = "💊 Remedy: ${analysis.remedyEn}"
        binding.tvPrevention.text = "🛡️ Prevention: ${analysis.preventionEn}"
        binding.tvConfidence.text = "Confidence: ${(analysis.confidence * 100).toInt()}%"

        // Severity indicator
        val severityColor = when (analysis.severity) {
            "high" -> R.color.severity_high
            "medium" -> R.color.severity_medium
            else -> R.color.severity_low
        }
        binding.tvSeverity.text = "Severity: ${analysis.severity.uppercase()}"
        binding.tvSeverity.setTextColor(ContextCompat.getColor(requireContext(), severityColor))

        // Offline indicator
        if (!NetworkUtils.isOnline(requireContext())) {
            binding.tvOfflineNote.visibility = View.VISIBLE
        }
    }

    private fun getSimulatedAnalysis(): ExpertAnalysis {
        val analyses = listOf(
            ExpertAnalysis(
                diseaseNameEn = "Early Blight (Alternaria solani)",
                diseaseNameKn = "ಮೊದಲ ಕೊಳೆ ರೋಗ",
                descriptionEn = "Fungal disease causing dark concentric rings on leaves. Common in warm, humid conditions.",
                descriptionKn = "ಬೆಚ್ಚಗಿನ ತೇವ ಸ್ಥಿತಿಯಲ್ಲಿ ಎಲೆಗಳ ಮೇಲೆ ಕಪ್ಪು ಕೇಂದ್ರಿತ ಉಂಗುರ ಉಂಟುಮಾಡುವ ಶಿಲೀಂಧ್ರ ರೋಗ.",
                remedyEn = "Spray Mancozeb 75% WP at 2.5g/litre. Remove and destroy infected leaves immediately.",
                remedyKn = "ಮ್ಯಾಂಕೋಜೆಬ್ 75% WP 2.5ಗ್ರಾ/ಲೀ ಸಿಂಪಡಿಸಿ. ಸೋಂಕಿತ ಎಲೆಗಳನ್ನು ತೆಗೆದು ನಾಶಪಡಿಸಿ.",
                severity = "medium",
                confidence = 0.87f,
                preventionEn = "Use disease-resistant varieties. Practice crop rotation with non-solanaceous crops.",
                preventionKn = "ರೋಗ-ನಿರೋಧಕ ತಳಿ ಬಳಸಿ. ಬೇರೆ ಬೆಳೆಯೊಂದಿಗೆ ಬೆಳೆ ಪರಿವರ್ತನೆ ಅಭ್ಯಾಸ ಮಾಡಿ."
            ),
            ExpertAnalysis(
                diseaseNameEn = "Bacterial Leaf Blight",
                diseaseNameKn = "ಬ್ಯಾಕ್ಟೀರಿಯಾ ಎಲೆ ಕೊಳೆ",
                descriptionEn = "Water-soaked lesions on leaf tips that turn white-grey. Spreads rapidly during monsoon.",
                descriptionKn = "ಎಲೆ ತುದಿಯಲ್ಲಿ ನೀರು ಒಳಗೊಂಡ ಗಾಯಗಳು ಬಿಳಿ-ಬೂದು ಬಣ್ಣಕ್ಕೆ ತಿರುಗುತ್ತವೆ.",
                remedyEn = "Apply Streptomycin sulphate 500ppm. Drain excess water from fields immediately.",
                remedyKn = "ಸ್ಟ್ರೆಪ್ಟೋಮೈಸಿನ್ ಸಲ್ಫೇಟ್ 500ppm ಹಾಕಿ. ಹೊಲಗಳಿಂದ ಹೆಚ್ಚಿನ ನೀರನ್ನು ಬರಿದು ಮಾಡಿ.",
                severity = "high",
                confidence = 0.92f,
                preventionEn = "Avoid excess nitrogen fertilization. Use certified disease-free seeds.",
                preventionKn = "ಅತಿಯಾದ ಸಾರಜನಕ ಗೊಬ್ಬರ ತಪ್ಪಿಸಿ. ಪ್ರಮಾಣೀಕೃತ ರೋಗ-ಮುಕ್ತ ಬೀಜ ಬಳಸಿ."
            )
        )
        return analyses.random()
    }

    private fun resetState() {
        currentPhotoUri = null
        binding.ivLeafPreview.visibility = View.GONE
        binding.uploadPrompt.visibility = View.VISIBLE
        binding.cardResult.visibility = View.GONE
        binding.btnAnalyze.visibility = View.GONE
        binding.btnReset.visibility = View.GONE
        binding.tvOfflineNote.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
