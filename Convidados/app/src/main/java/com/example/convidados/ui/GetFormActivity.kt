package com.example.convidados.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.GetModel
import com.example.convidados.R
import com.example.convidados.databinding.ActivityGetFormBinding
import com.example.convidados.ViewModel.GetFormViewModel

class GetFormActivity : AppCompatActivity(), View.OnClickListener {
     private lateinit var binding:ActivityGetFormBinding
     private lateinit var  viewModel: GetFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityGetFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(GetFormViewModel::class.java)
        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked=true
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View) {
        if(v.id== R.id.button_save){
            val name= binding.editName.text.toString()
            val presence= binding.radioPresent.isChecked
            val model= GetModel(0, name, presence)
            viewModel.insert(model)
        }
    }
}