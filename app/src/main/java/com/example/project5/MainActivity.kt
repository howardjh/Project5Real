package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import androidx.lifecycle.ViewModelProvider
import com.example.project5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TranslateViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val inputFragment = InputFragment()
        fragmentTransaction.replace(R.id.input_layout, inputFragment)
        fragmentTransaction.commit()

        binding.inputEnglish.setOnClickListener {
            viewModel.setExpectedLangTag("English")
        }

        binding.inputSpanish.setOnClickListener {
            viewModel.setExpectedLangTag("Spanish")
        }

        binding.inputGerman.setOnClickListener {
            viewModel.setExpectedLangTag("German")
        }

        binding.translateEnglish.setOnClickListener {
            viewModel.setTranslateToTag("English")
        }

        binding.translateGerman.setOnClickListener {
            viewModel.setTranslateToTag("German")
        }

        binding.translateSpanish.setOnClickListener {
            viewModel.setTranslateToTag("Spanish")
        }

        viewModel.translatedText.observe(this) { translatedText ->
            binding.tvTranslated.text = translatedText
        }

    }

}

