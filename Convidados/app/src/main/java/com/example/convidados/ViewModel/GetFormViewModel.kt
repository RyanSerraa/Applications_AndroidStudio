package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidados.GetModel
import com.example.convidados.repository.GetRepository

class GetFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository= GetRepository.getInstance(application)
    fun insert(guest:GetModel){
        repository.insert(guest)

    }
}