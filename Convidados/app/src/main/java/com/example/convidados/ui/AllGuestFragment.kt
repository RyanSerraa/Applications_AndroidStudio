package com.example.convidados.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.ViewModel.AllGuestViewModel
import com.example.convidados.databinding.FragmentAllGuestBinding

class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)
        _binding = FragmentAllGuestBinding.inflate(inflater, container, false)
        binding.recyclerAllGuest.layoutMananger= LinearLayoutManager(context)
        homeViewModel.getAll()
        observe()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            val v=""

        }
    }
}