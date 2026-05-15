# Default ProGuard rules
-keepclassmembers class * implements android.os.Parcelable { *; }
-keep class com.raithavarta.app.data.model.** { *; }
-dontwarn com.google.ai.client.**
