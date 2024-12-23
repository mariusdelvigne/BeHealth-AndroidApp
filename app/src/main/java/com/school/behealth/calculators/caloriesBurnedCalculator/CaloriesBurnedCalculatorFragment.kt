package com.school.behealth.calculators.caloriesBurnedCalculator

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.databinding.FragmentCaloriesBurnedCalculatorBinding

class CaloriesBurnedCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCaloriesBurnedCalculatorBinding
    private val viewModel: CalculateCaloriesBurnedManagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaloriesBurnedCalculatorBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance() = CaloriesBurnedCalculatorFragment()
    }

}