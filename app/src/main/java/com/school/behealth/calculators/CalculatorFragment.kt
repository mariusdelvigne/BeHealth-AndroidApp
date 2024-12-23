package com.school.behealth.calculators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = CalculatorFragment()
    }
}