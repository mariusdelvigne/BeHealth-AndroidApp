package com.school.behealth.calculators.bmiCalculator

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.databinding.FragmentCalculateBmiManagerBinding

class CalculateBmiManagerFragment : Fragment() {
    private lateinit var binding: FragmentCalculateBmiManagerBinding

    companion object {
        fun newInstance() = CalculateBmiManagerFragment()
    }

    private val viewModel: CalculateBmiManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculateBmiManagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}