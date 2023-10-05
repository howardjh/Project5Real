package com.example.project5

import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateViewModel : ViewModel() {
    private val languageIdentifier = LanguageIdentification.getClient()
    private var expectedLangTag = ""
    private var translateToTag = ""
    private var conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()
    private val enesOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    private val englishToSpanish = Translation.getClient(enesOptions)
    private val endeOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build()
    private val englishToGerman = Translation.getClient(endeOptions)
    private val esenOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.SPANISH)
        .setTargetLanguage(TranslateLanguage.ENGLISH)
        .build()
    private val spanishToEnglish = Translation.getClient(esenOptions)
    private val esdeOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.SPANISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build()
    private val spanishToGerman = Translation.getClient(esdeOptions)
    private val deenOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.GERMAN)
        .setTargetLanguage(TranslateLanguage.ENGLISH)
        .build()
    private val germanToEnglish = Translation.getClient(deenOptions)
    private val deesOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.GERMAN)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()
    private val germanToSpanish = Translation.getClient(deesOptions)

    // LiveData to hold user input text
    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    // LiveData to hold translated text
    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String> = _translatedText

    // Function to update input text
    fun setInputText(text: String) {
        _inputText.value = text
    }
    private fun setTranslatedText(text: String) {
        _translatedText.value = text
    }

    fun setTranslateToTag(text: String){
        translateToTag = text
    }
    fun setExpectedLangTag(text: String){
        expectedLangTag = text
    }


    // Function to translate text
    fun translateText(text: String) {
        var translated = ""
        if(text.isEmpty()){ }
        else{
            if(expectedLangTag == "English" && translateToTag == "Spanish"){
                englishToSpanish.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        englishToSpanish.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            } else if (expectedLangTag == "English" && translateToTag == "German"){
                englishToGerman.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        englishToGerman.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            } else if(expectedLangTag == "Spanish" && translateToTag == "English"){
                spanishToEnglish.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener{
                        spanishToEnglish.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            } else if(expectedLangTag == "Spanish" && translateToTag == "German"){
                spanishToGerman.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        spanishToGerman.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            } else if(expectedLangTag == "German" && translateToTag == "English"){
                germanToEnglish.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        germanToEnglish.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            } else{
                germanToSpanish.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        germanToEnglish.translate(text).addOnSuccessListener {
                            translated = it
                            setTranslatedText(translated)
                        }
                    }
            }
        }
        Log.d("Translated Text", translated)
    }

}
