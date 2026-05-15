package com.raithavarta.app.data.seed

import com.raithavarta.app.data.model.Tip
import com.raithavarta.app.data.model.SuccessStory

/**
 * Pre-loaded seed data for first launch.
 * Contains tips in English, Kannada, and Hindi for all 4 crop categories.
 */
object SeedData {

    val tips = listOf(
        // ── Paddy Tips ───────────────────────────────────
        Tip(
            titleEn = "Blast Disease Alert",
            titleKn = "ಬ್ಲಾಸ್ಟ್ ರೋಗ ಎಚ್ಚರಿಕೆ",
            titleHi = "ब्लास्ट रोग की चेतावनी",
            descriptionEn = "Paddy blast causes diamond-shaped spots on leaves. High humidity increases risk significantly.",
            descriptionKn = "ಭತ್ತದ ಬ್ಲಾಸ್ಟ್ ರೋಗವು ಎಲೆಗಳ ಮೇಲೆ ವಜ್ರಾಕಾರದ ಕಲೆಗಳನ್ನು ಉಂಟುಮಾಡುತ್ತದೆ.",
            descriptionHi = "धान के ब्लास्ट रोग से पत्तियों पर हीरे के आकार के धब्बे हो जाते हैं। उच्च आर्द्रता जोखिम को बढ़ाती है।",
            actionEn = "Spray Tricyclazole 75% WP at 0.6g/litre. Apply in the evening for best results.",
            actionKn = "ಟ್ರೈಸೈಕ್ಲಾಜೋಲ್ 75% WP ಅನ್ನು 0.6ಗ್ರಾ/ಲೀಟರ್ ಸಿಂಪಡಿಸಿ. ಸಂಜೆ ಸಿಂಪಡಿಸಿ.",
            actionHi = "ट्राइसाइक्लोज़ोल 75% WP का 0.6g/लीटर की दर से छिड़काव करें। शाम को करें।",
            cropCategory = "paddy",
            imageResName = "tip_paddy_blast",
            tipType = "pest",
            season = "kharif",
            priority = 10
        ),
        Tip(
            titleEn = "Best Time to Transplant",
            titleKn = "ನಾಟಿ ಮಾಡಲು ಉತ್ತಮ ಸಮಯ",
            titleHi = "रोपाई का सबसे अच्छा समय",
            descriptionEn = "Transplant paddy seedlings when they are 20-25 days old. Plant 2-3 seedlings per hill.",
            descriptionKn = "20-25 ದಿನಗಳ ಸಸಿಗಳನ್ನು ನಾಟಿ ಮಾಡಿ. ಪ್ರತಿ ಗುಂಡಿಗೆ 2-3 ಸಸಿಗಳನ್ನು ನೆಡಿ.",
            descriptionHi = "धान के पौधों की रोपाई तब करें जब वे 20-25 दिन के हो जाएं। प्रति गड्ढे में 2-3 पौधे लगाएं।",
            actionEn = "Maintain 20x15 cm spacing between rows. Keep 2-3 cm standing water after transplanting.",
            actionKn = "ಸಾಲುಗಳ ನಡುವೆ 20x15 ಸೆಂ.ಮೀ ಅಂತರ ಕಾಪಾಡಿ. ನಾಟಿ ನಂತರ 2-3 ಸೆಂ.ಮೀ ನೀರು ನಿಲ್ಲಿಸಿ.",
            actionHi = "पंक्तियों के बीच 20x15 सेमी की दूरी बनाए रखें। रोपाई के बाद 2-3 सेमी पानी रखें।",
            cropCategory = "paddy",
            imageResName = "tip_paddy_transplant",
            tipType = "general",
            season = "kharif",
            priority = 9
        ),
        Tip(
            titleEn = "Weed Control Window",
            titleKn = "ಕಳೆ ನಿಯಂತ್ರಣ ಸಮಯ",
            titleHi = "खरपतवार नियंत्रण का समय",
            descriptionEn = "First 40 days after transplanting are critical. Weeds compete for nutrients and reduce yield by 30-50%.",
            descriptionKn = "ನಾಟಿ ನಂತರ ಮೊದಲ 40 ದಿನಗಳು ನಿರ್ಣಾಯಕ. ಕಳೆಗಳು ಇಳುವರಿಯನ್ನು 30-50% ಕಡಿಮೆ ಮಾಡುತ್ತವೆ.",
            descriptionHi = "रोपाई के बाद पहले 40 दिन महत्वपूर्ण हैं। खरपतवार पोषक तत्वों के लिए प्रतिस्पर्धा करते हैं और उपज को 30-50% तक कम कर देते हैं।",
            actionEn = "Apply Butachlor 50EC at 2.5 litre/hectare within 3 days of transplanting.",
            actionKn = "ನಾಟಿ 3 ದಿನಗಳೊಳಗೆ ಬ್ಯೂಟಾಕ್ಲೋರ್ 50EC 2.5 ಲೀ/ಹೆಕ್ಟೇರ್ ಸಿಂಪಡಿಸಿ.",
            actionHi = "रोपाई के 3 दिनों के भीतर ब्यूटाक्लोर 50EC 2.5 लीटर/हेक्टेयर डालें।",
            cropCategory = "paddy",
            imageResName = "tip_paddy_weed",
            tipType = "general",
            season = "kharif",
            priority = 8
        ),
        Tip(
            titleEn = "Water Management Tips",
            titleKn = "ನೀರು ನಿರ್ವಹಣೆ ಸಲಹೆ",
            titleHi = "जल प्रबंधन के नुस्खे",
            descriptionEn = "Alternate wetting and drying (AWD) saves 25% water without reducing yield. Drain field 10 days before harvest.",
            descriptionKn = "ಪರ್ಯಾಯ ಒದ್ದೆ ಮತ್ತು ಒಣಗಿಸುವಿಕೆ 25% ನೀರು ಉಳಿಸುತ್ತದೆ. ಕೊಯ್ಲಿಗೆ 10 ದಿನ ಮೊದಲು ನೀರು ಬರಿದು ಮಾಡಿ.",
            descriptionHi = "वैकल्पिक रूप से गीला और सूखा (AWD) करना उपज कम किए बिना 25% पानी बचाता है। कटाई से 10 दिन पहले खेत को सूखा लें।",
            actionEn = "Let water level drop to 15cm below soil surface, then re-flood to 5cm depth.",
            actionKn = "ನೀರಿನ ಮಟ್ಟವನ್ನು ಮಣ್ಣಿನ ಕೆಳಗೆ 15ಸೆಂಮೀ ಇಳಿಸಿ, ನಂತರ 5ಸೆಂಮೀ ಆಳಕ್ಕೆ ಮರುತುಂಬಿಸಿ.",
            actionHi = "पानी के स्तर को मिट्टी की सतह से 15 सेमी नीचे आने दें, फिर 5 सेमी गहराई तक भरें।",
            cropCategory = "paddy",
            imageResName = "tip_paddy_water",
            tipType = "water",
            season = "all",
            priority = 7
        ),

        // ── Areca Nut Tips ───────────────────────────────
        Tip(
            titleEn = "Yellow Leaf Disease Warning",
            titleKn = "ಹಳದಿ ಎಲೆ ರೋಗ ಎಚ್ಚರಿಕೆ",
            titleHi = "पीले पत्ते की बीमारी की चेतावनी",
            descriptionEn = "Yellow leaf disease is caused by phytoplasma. Leaves turn yellow from tips. No cure exists — prevention is key.",
            descriptionKn = "ಹಳದಿ ಎಲೆ ರೋಗವು ಫೈಟೋಪ್ಲಾಸ್ಮಾದಿಂದ ಉಂಟಾಗುತ್ತದೆ. ತಡೆಗಟ್ಟುವಿಕೆ ಪ್ರಮುಖ.",
            descriptionHi = "पीले पत्ते की बीमारी फाइटोप्लाज्मा के कारण होती है। पत्ते सिरे से पीले हो जाते हैं। कोई इलाज नहीं है — रोकथाम महत्वपूर्ण है।",
            actionEn = "Remove and burn infected palms immediately. Apply Neem cake around healthy palms.",
            actionKn = "ಸೋಂಕಿತ ಮರಗಳನ್ನು ತೆಗೆದು ಸುಡಿ. ಆರೋಗ್ಯಕರ ಮರಗಳ ಸುತ್ತ ಬೇವಿನ ಹಿಂಡಿ ಹಾಕಿ.",
            actionHi = "संक्रमित पेड़ों को तुरंत हटाकर जला दें। स्वस्थ पेड़ों के आसपास नीम की खली डालें।",
            cropCategory = "areca_nut",
            imageResName = "tip_areca_yellow",
            tipType = "pest",
            season = "all",
            priority = 10
        ),
        Tip(
            titleEn = "Ideal Spacing for Plantation",
            titleKn = "ತೋಟಕ್ಕೆ ಆದರ್ಶ ಅಂತರ",
            titleHi = "वृक्षारोपण के लिए आदर्श दूरी",
            descriptionEn = "Proper spacing ensures sunlight penetration and air circulation, reducing fungal diseases.",
            descriptionKn = "ಸರಿಯಾದ ಅಂತರವು ಸೂರ್ಯನ ಬೆಳಕು ಮತ್ತು ಗಾಳಿ ಪ್ರಸರಣವನ್ನು ಖಚಿತಪಡಿಸುತ್ತದೆ.",
            descriptionHi = "उचित दूरी धूप और हवा का संचार सुनिश्चित करती है, जिससे फंगल बीमारियाँ कम होती हैं।",
            actionEn = "Plant at 2.7m x 2.7m spacing. Dig pits of 90cm x 90cm x 90cm before monsoon.",
            actionKn = "2.7ಮೀ x 2.7ಮೀ ಅಂತರದಲ್ಲಿ ನೆಡಿ. ಮಳೆಗಾಲಕ್ಕೆ ಮೊದಲು 90ಸೆಂಮೀ ಗುಂಡಿ ತೋಡಿ.",
            actionHi = "2.7m x 2.7m की दूरी पर लगाएं। मानसून से पहले 90x90x90 सेमी के गड्ढे खोदें।",
            cropCategory = "areca_nut",
            imageResName = "tip_areca_spacing",
            tipType = "general",
            season = "kharif",
            priority = 8
        ),
        Tip(
            titleEn = "Koleroga (Fruit Rot) Prevention",
            titleKn = "ಕೊಳೆರೋಗ ತಡೆಗಟ್ಟುವಿಕೆ",
            titleHi = "कोलेरोगा (फल सड़न) की रोकथाम",
            descriptionEn = "Koleroga causes fruit drop during monsoon. It can destroy 50-70% of the crop if untreated.",
            descriptionKn = "ಕೊಳೆರೋಗವು ಮಳೆಗಾಲದಲ್ಲಿ ಹಣ್ಣು ಉದುರಲು ಕಾರಣವಾಗುತ್ತದೆ. 50-70% ಬೆಳೆ ಹಾಳಾಗಬಹುದು.",
            descriptionHi = "कोलेरोगा मानसून के दौरान फल गिरने का कारण बनता है। उपचार न होने पर 50-70% फसल नष्ट हो सकती है।",
            actionEn = "Spray 1% Bordeaux mixture before monsoon onset. Repeat every 40 days during rains.",
            actionKn = "ಮಳೆಗಾಲ ಆರಂಭಕ್ಕೆ ಮೊದಲು 1% ಬೋರ್ಡೋ ಮಿಶ್ರಣ ಸಿಂಪಡಿಸಿ. ಮಳೆಯಲ್ಲಿ 40 ದಿನಕ್ಕೊಮ್ಮೆ ಪುನರಾವರ್ತಿಸಿ.",
            actionHi = "मानसून शुरू होने से पहले 1% बोर्डो मिश्रण का छिड़काव करें। बारिश के दौरान हर 40 दिन पर दोहराएं।",
            cropCategory = "areca_nut",
            imageResName = "tip_areca_koleroga",
            tipType = "pest",
            season = "kharif",
            priority = 9
        ),

        // ── Coconut Tips ─────────────────────────────────
        Tip(
            titleEn = "Rhinoceros Beetle Attack",
            titleKn = "ಘೇಂಡಾಮೃಗ ಜೀರುಂಡೆ ದಾಳಿ",
            titleHi = "राइनोसेरोस बीटल का हमला",
            descriptionEn = "The rhinoceros beetle bores into coconut crowns. V-shaped cuts on fronds are early signs.",
            descriptionKn = "ಘೇಂಡಾಮೃಗ ಜೀರುಂಡೆ ತೆಂಗಿನ ತುದಿಗೆ ಕೊರೆಯುತ್ತದೆ. ಎಲೆಗಳಲ್ಲಿ V-ಆಕಾರದ ಕತ್ತರಿ ಮೊದಲ ಚಿಹ್ನೆ.",
            descriptionHi = "राइनोसेरोस बीटल नारियल के मुकुट में छेद कर देता है। पत्तों पर V-आकार के कट शुरुआती लक्षण हैं।",
            actionEn = "Fill leaf axils with Naphthalene balls (3 per palm) + sand. Place pheromone traps nearby.",
            actionKn = "ಎಲೆ ಬುಡಗಳಲ್ಲಿ ನ್ಯಾಫ್ಥಲೀನ್ ಚೆಂಡುಗಳು (ಮರಕ್ಕೆ 3) + ಮರಳು ತುಂಬಿಸಿ.",
            actionHi = "पत्तियों के कक्ष में नेफ़थलीन की गोलियाँ (प्रति पेड़ 3) + रेत भरें।",
            cropCategory = "coconut",
            imageResName = "tip_coconut_beetle",
            tipType = "pest",
            season = "all",
            priority = 10
        ),
        Tip(
            titleEn = "Fertilizer Schedule",
            titleKn = "ಗೊಬ್ಬರ ವೇಳಾಪಟ್ಟಿ",
            titleHi = "उर्वरक का कार्यक्रम",
            descriptionEn = "Adult coconut palms need balanced nutrition. Apply fertilizers in two splits — June and December.",
            descriptionKn = "ವಯಸ್ಕ ತೆಂಗಿನ ಮರಗಳಿಗೆ ಸಮತೋಲಿತ ಪೋಷಣೆ ಬೇಕು. ಜೂನ್ ಮತ್ತು ಡಿಸೆಂಬರ್ ನಲ್ಲಿ ಹಾಕಿ.",
            descriptionHi = "वयस्क नारियल के पेड़ों को संतुलित पोषण की आवश्यकता होती है। जून और दिसंबर में दो बार उर्वरक डालें।",
            actionEn = "Per palm: 500g Urea + 800g Super Phosphate + 1200g Muriate of Potash per year.",
            actionKn = "ಪ್ರತಿ ಮರಕ್ಕೆ: 500ಗ್ರಾ ಯೂರಿಯಾ + 800ಗ್ರಾ ಸೂಪರ್ ಫಾಸ್ಫೇಟ್ + 1200ಗ್ರಾ ಪೊಟಾಷ್/ವರ್ಷ.",
            actionHi = "प्रति पेड़: 500 ग्राम यूरिया + 800 ग्राम सुपर फॉस्फेट + 1200 ग्राम पोटाश प्रति वर्ष।",
            cropCategory = "coconut",
            imageResName = "tip_coconut_fertilizer",
            tipType = "fertilizer",
            season = "all",
            priority = 9
        ),
        Tip(
            titleEn = "Harvesting at Right Stage",
            titleKn = "ಸರಿಯಾದ ಹಂತದಲ್ಲಿ ಕೊಯ್ಲು",
            titleHi = "सही अवस्था में कटाई",
            descriptionEn = "Harvest coconuts 11-12 months after flowering. Brown, dry husk indicates maturity.",
            descriptionKn = "ಹೂಬಿಟ್ಟ 11-12 ತಿಂಗಳ ನಂತರ ತೆಂಗಿನಕಾಯಿ ಕೊಯ್ಲು ಮಾಡಿ. ಕಂದು ಒಣ ಸಿಪ್ಪೆ ಪಕ್ವತೆ ಸೂಚಿಸುತ್ತದೆ.",
            descriptionHi = "फूल आने के 11-12 महीने बाद नारियल की कटाई करें। भूरा, सूखा छिलका परिपक्वता को दर्शाता है।",
            actionEn = "Harvest every 45-60 days. Use trained climbers or coconut climbing machines for safety.",
            actionKn = "ಪ್ರತಿ 45-60 ದಿನಗಳಿಗೊಮ್ಮೆ ಕೊಯ್ಲು ಮಾಡಿ. ಸುರಕ್ಷತೆಗೆ ಹತ್ತುವ ಯಂತ್ರ ಬಳಸಿ.",
            actionHi = "हर 45-60 दिनों में कटाई करें। सुरक्षा के लिए नारियल पर चढ़ने वाली मशीनों का उपयोग करें।",
            cropCategory = "coconut",
            imageResName = "tip_coconut_harvest",
            tipType = "harvest",
            season = "all",
            priority = 7
        ),

        // ── Tomato Tips ──────────────────────────────────
        Tip(
            titleEn = "Early Blight Prevention",
            titleKn = "ಮೊದಲ ಕೊಳೆ ರೋಗ ತಡೆ",
            titleHi = "प्रारंभिक झुलसा की रोकथाम",
            descriptionEn = "Dark concentric rings on lower leaves signal early blight. It spreads fast in humid conditions.",
            descriptionKn = "ಕೆಳಗಿನ ಎಲೆಗಳಲ್ಲಿ ಕಪ್ಪು ಕೇಂದ್ರಿತ ಉಂಗುರಗಳು ಮೊದಲ ಕೊಳೆ ಸೂಚಿಸುತ್ತವೆ.",
            descriptionHi = "निचली पत्तियों पर काले छल्ले प्रारंभिक झुलसा का संकेत हैं। यह नम स्थितियों में तेज़ी से फैलता है।",
            actionEn = "Spray Mancozeb 75% WP at 2.5g/litre at first sign. Remove infected lower leaves.",
            actionKn = "ಮೊದಲ ಚಿಹ್ನೆಯಲ್ಲಿ ಮ್ಯಾಂಕೋಜೆಬ್ 75% WP 2.5ಗ್ರಾ/ಲೀ ಸಿಂಪಡಿಸಿ. ಸೋಂಕಿತ ಎಲೆ ತೆಗೆಯಿರಿ.",
            actionHi = "पहले संकेत पर मैनकोज़ेब 75% WP का 2.5g/लीटर की दर से छिड़काव करें।",
            cropCategory = "tomato",
            imageResName = "tip_tomato_blight",
            tipType = "pest",
            season = "all",
            priority = 10
        ),
        Tip(
            titleEn = "Staking for Better Yield",
            titleKn = "ಉತ್ತಮ ಇಳುವರಿಗೆ ಬೆಂಬಲ ಕೋಲು",
            titleHi = "बेहतर उपज के लिए स्टैकिंग",
            descriptionEn = "Staking prevents fruit from touching soil, reducing rot. It also improves air circulation.",
            descriptionKn = "ಬೆಂಬಲ ಕೋಲು ಹಣ್ಣು ಮಣ್ಣಿನ ಸಂಪರ್ಕ ತಡೆಯುತ್ತದೆ, ಕೊಳೆತ ಕಡಿಮೆ ಮಾಡುತ್ತದೆ.",
            descriptionHi = "स्टैकिंग से फल मिट्टी को नहीं छूते और सड़न कम होती है। यह हवा का संचार भी सुधारता है।",
            actionEn = "Use 1.5m bamboo stakes. Tie plants with jute string at 3 points as they grow.",
            actionKn = "1.5ಮೀ ಬಿದಿರು ಕೋಲು ಬಳಸಿ. ಬೆಳೆಯುವಾಗ ಸೆಣಬಿನ ದಾರದಿಂದ 3 ಕಡೆ ಕಟ್ಟಿ.",
            actionHi = "1.5 मीटर बांस का प्रयोग करें। पौधों को बढ़ते समय 3 बिंदुओं पर बांधें।",
            cropCategory = "tomato",
            imageResName = "tip_tomato_staking",
            tipType = "general",
            season = "rabi",
            priority = 8
        ),
        Tip(
            titleEn = "Whitefly & Virus Control",
            titleKn = "ಬಿಳಿ ನೊಣ ಮತ್ತು ವೈರಸ್ ನಿಯಂತ್ರಣ",
            titleHi = "सफ़ेद मक्खी और वायरस नियंत्रण",
            descriptionEn = "Whiteflies transmit Tomato Leaf Curl Virus (ToLCV). Curled, yellowed leaves are symptoms.",
            descriptionKn = "ಬಿಳಿ ನೊಣಗಳು ಟೊಮೆಟೊ ಎಲೆ ಸುರುಳಿ ವೈರಸ್ ಹರಡುತ್ತವೆ. ಸುರುಳಿ ಹಳದಿ ಎಲೆ ಲಕ್ಷಣ.",
            descriptionHi = "सफ़ेद मक्खियां लीफ़ कर्ल वायरस फैलाती हैं। मुड़ी हुई पीली पत्तियां इसके लक्षण हैं।",
            actionEn = "Install yellow sticky traps. Spray Imidacloprid 0.3ml/litre at 15-day intervals.",
            actionKn = "ಹಳದಿ ಅಂಟು ಬಲೆ ಹಾಕಿ. ಇಮಿಡಾಕ್ಲೋಪ್ರಿಡ್ 0.3ಮಿಲೀ/ಲೀ 15 ದಿನಕ್ಕೊಮ್ಮೆ ಸಿಂಪಡಿಸಿ.",
            actionHi = "पीले चिपचिपे जाल लगाएं। हर 15 दिनों में इमिडाक्लोप्रिड का छिड़काव करें।",
            cropCategory = "tomato",
            imageResName = "tip_tomato_whitefly",
            tipType = "pest",
            season = "all",
            priority = 9
        ),
        Tip(
            titleEn = "Optimal Watering Schedule",
            titleKn = "ಅತ್ಯುತ್ತಮ ನೀರಾವರಿ ವೇಳಾಪಟ್ಟಿ",
            titleHi = "अत्युत्तम सिंचाई का समय",
            descriptionEn = "Irregular watering causes blossom end rot and fruit cracking. Consistency is crucial.",
            descriptionKn = "ಅನಿಯಮಿತ ನೀರಾವರಿ ಹೂ ತುದಿ ಕೊಳೆತ ಮತ್ತು ಹಣ್ಣು ಬಿರುಕಿಗೆ ಕಾರಣ.",
            descriptionHi = "अनियमित सिंचाई से फल फटने लगते हैं। निरंतरता महत्वपूर्ण है।",
            actionEn = "Use drip irrigation at 2-4 litres/plant/day. Water in morning, avoid wetting leaves.",
            actionKn = "ಹನಿ ನೀರಾವರಿ 2-4 ಲೀ/ಗಿಡ/ದಿನ ಬಳಸಿ. ಬೆಳಿಗ್ಗೆ ನೀರು ಹಾಕಿ, ಎಲೆ ಒದ್ದೆ ಮಾಡಬೇಡಿ.",
            actionHi = "ड्रिप सिंचाई का उपयोग करें। सुबह पानी दें, पत्तों को गीला होने से बचाएं।",
            cropCategory = "tomato",
            imageResName = "tip_tomato_water",
            tipType = "water",
            season = "all",
            priority = 7
        )
    )

    val stories = listOf(
        SuccessStory(
            farmerNameEn = "Ramesh Gowda",
            farmerNameKn = "ರಮೇಶ್ ಗೌಡ",
            farmerNameHi = "रमेश गौड़ा",
            locationEn = "Mandya, Karnataka",
            locationKn = "ಮಂಡ್ಯ, ಕರ್ನಾಟಕ",
            locationHi = "मैंड्या, कर्नाटक",
            storyEn = "After following the paddy blast tip from Raitha-Varta, Ramesh saved his entire 2-acre crop. He sprayed Tricyclazole early and prevented a 40% yield loss that his neighbors experienced.",
            storyKn = "ರೈಥ-ವಾರ್ತಾದ ಭತ್ತ ಬ್ಲಾಸ್ಟ್ ಸಲಹೆಯನ್ನು ಅನುಸರಿಸಿ ರಮೇಶ್ ತಮ್ಮ 2 ಎಕರೆ ಬೆಳೆಯನ್ನು ಉಳಿಸಿದರು.",
            storyHi = "रायथा-वार्ता के नुस्खों का पालन करने के बाद, रमेश ने अपनी पूरी 2 एकड़ फसल बचा ली।",
            cropCategory = "paddy",
            yieldImprovement = "40% loss prevented",
            imageResName = "story_ramesh"
        ),
        SuccessStory(
            farmerNameEn = "Lakshmi Devi",
            farmerNameKn = "ಲಕ್ಷ್ಮಿ ದೇವಿ",
            farmerNameHi = "लक्ष्मी देवी",
            locationEn = "Shimoga, Karnataka",
            locationKn = "ಶಿವಮೊಗ್ಗ, ಕರ್ನಾಟಕ",
            locationHi = "शिमोगा, कर्नाटक",
            storyEn = "Lakshmi used the Koleroga prevention tip for her areca nut plantation. By spraying Bordeaux mixture on time, she saved 70% of her crop worth ₹2 lakhs.",
            storyKn = "ಲಕ್ಷ್ಮಿ ಕೊಳೆರೋಗ ತಡೆ ಸಲಹೆ ಬಳಸಿ ಅಡಿಕೆ ತೋಟ ರಕ್ಷಿಸಿದರು. ₹2 ಲಕ್ಷ ಬೆಳೆ ಉಳಿಸಿದರು.",
            storyHi = "लक्ष्मी ने कोलेरोगा रोकथाम युक्ति का उपयोग किया और अपनी फसल का 70% बचाया।",
            cropCategory = "areca_nut",
            yieldImprovement = "₹2 Lakh saved",
            imageResName = "story_lakshmi"
        ),
        SuccessStory(
            farmerNameEn = "Nagaraj B.",
            farmerNameKn = "ನಾಗರಾಜ್ ಬಿ.",
            farmerNameHi = "नागराज बी.",
            locationEn = "Tumkur, Karnataka",
            locationKn = "ತುಮಕೂರು, ಕರ್ನಾಟಕ",
            locationHi = "तुमकुर, कर्नाटक",
            storyEn = "Nagaraj followed the coconut fertilizer schedule tip. His copra yield increased by 25% in just one year. He now recommends Raitha-Varta to all farmers in his village.",
            storyKn = "ನಾಗರಾಜ್ ತೆಂಗಿನ ಗೊಬ್ಬರ ವೇಳಾಪಟ್ಟಿ ಅನುಸರಿಸಿದರು. ಕೊಬ್ಬರಿ ಇಳುವರಿ 25% ಹೆಚ್ಚಾಯಿತು.",
            storyHi = "नागराज ने उर्वरक कार्यक्रम का पालन किया और उनकी उपज में 25% की वृद्धि हुई।",
            cropCategory = "coconut",
            yieldImprovement = "25% yield increase",
            imageResName = "story_nagaraj"
        ),
        SuccessStory(
            farmerNameEn = "Savitha K.",
            farmerNameKn = "ಸವಿತಾ ಕೆ.",
            farmerNameHi = "सविता के.",
            locationEn = "Kolar, Karnataka",
            locationKn = "ಕೋಲಾರ, ಕರ್ನಾಟಕ",
            locationHi = "कोलार, कर्नाटक",
            storyEn = "Savitha's tomato crop was being destroyed by whiteflies. She used the yellow sticky trap tip and Imidacloprid spray schedule from Raitha-Varta. Her harvest doubled compared to last season.",
            storyKn = "ಸವಿತಾ ಅವರ ಟೊಮ್ಯಾಟೊ ಬೆಳೆ ಬಿಳಿ ನೊಣದಿಂದ ನಾಶವಾಗುತ್ತಿತ್ತು. ರೈಥ-ವಾರ್ತಾ ಸಲಹೆ ಬಳಸಿ ಇಳುವರಿ ದ್ವಿಗುಣ.",
            storyHi = "सविता ने सफ़ेद मक्खी नियंत्रण युक्ति का उपयोग किया और उनकी उपज दोगुनी हो गई।",
            cropCategory = "tomato",
            yieldImprovement = "2x harvest",
            imageResName = "story_savitha"
        )
    )
}
