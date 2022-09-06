package com.hygge.hygge.faq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hygge.hygge.R
import com.hygge.hygge.databinding.FragmentAdmissionBinding
import com.hygge.hygge.databinding.FragmentEtcBinding


class FragmentEtc : Fragment() {
    private lateinit var binding: FragmentEtcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEtcBinding.inflate(inflater, container, false)
        return binding.root
    }
}