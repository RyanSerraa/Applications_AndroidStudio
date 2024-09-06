package com.example.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.SecurityPreferences
import com.example.motivation.data.Mock
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    View.OnClickListener { // Fechamento da classe MainActivity
    private lateinit var binding: ActivityMainBinding
    private var categoryID = MotivationConstants.filter.ALL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageAc.setOnClickListener(this)
        binding.imageAdd.setOnClickListener(this)
        handleUserName()
        handleNextPhrase()
        // Aplicar WindowInsets para ajustar as margens do sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    } // Fechamento do método onCreate

    override fun onClick(v: View) {
        if (v.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (v.id in listOf(R.id.image_ac, R.id.image_add, R.id.image_all)) {
            handleFilter(v.id)
        }
    }

    private fun handleNextPhrase() {
        val Phrases = Mock().getPhrase(categoryID)
        binding.textPhrase.text = Phrases
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageAc.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageAdd.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.filter.ALL
            }

            R.id.image_ac -> {
                binding.imageAc.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.filter.AC
            }

            else -> {
                binding.imageAdd.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryID = MotivationConstants.filter.ADD
            }
        }

    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.key.USER_NAME)
        binding.textKotlin.text = "Olá, $name"


    }

}
