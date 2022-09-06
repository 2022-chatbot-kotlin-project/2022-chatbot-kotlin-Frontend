package com.hygge.hygge.faq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hygge.hygge.R
import com.hygge.hygge.databinding.FragmentAdmissionBinding

class FragmentAdmission : Fragment() {
    private lateinit var binding: FragmentAdmissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdmissionBinding.inflate(inflater, container, false)
        return binding.root
    }
}