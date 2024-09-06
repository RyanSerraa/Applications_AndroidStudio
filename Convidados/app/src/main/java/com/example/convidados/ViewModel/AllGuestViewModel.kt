package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.GetModel
import com.example.convidados.repository.GetRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {
    private val repository= GetRepository.getInstance(application.applicationContext)
    private val listAllGuest = MutableLiveData<List<GetModel>>()
    val guests: LiveData<List<GetModel>> = listAllGuest
    fun getAll(){
        listAllGuest.value = repository.getAll()
    }
}