package com.hygge.hygge.faq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.hygge.hygge.R
import com.hygge.hygge.databinding.FragmentEtcBinding
import com.hygge.hygge.databinding.FragmentLifeBinding
import com.hygge.hygge.faq.Faq


class FragmentLife : Fragment() {

    private lateinit var binding: FragmentLifeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLifeBinding.inflate(inflater, container, false)
        return binding.root
    }

}