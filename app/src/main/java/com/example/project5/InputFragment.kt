package com.example.project5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project5.TranslateViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.project5.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private lateinit var viewModel: TranslateViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(requireActivity()).get(TranslateViewModel::class.java)
        var lastTranslateTime = 0L

        binding.inputText.addTextChangedListener {
            val now = System.currentTimeMillis()
            if (now - lastTranslateTime >= 500) {
                viewModel.setInputText(it.toString())
                Log.d("Input Text", binding.inputText.text.toString())
                viewModel.translateText(it.toString())
                lastTranslateTime = now
            }
        }
        return view
    }

}
