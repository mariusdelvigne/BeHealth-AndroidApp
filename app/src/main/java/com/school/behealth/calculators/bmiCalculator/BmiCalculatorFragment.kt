package com.school.behealth.calculators.bmiCalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.calculators.bmiCalculator.dtos.BmiCalculatorCommand
import com.school.behealth.databinding.FragmentBmiCalculatorBinding

class BmiCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentBmiCalculatorBinding
    private lateinit var viewModel: CalculateBmiManagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBmiCalculatorBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[CalculateBmiManagerViewModel::class.java]

        setUpSpinner()
        setOnClickListeners()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClickListeners() {
        binding.btnFragmentBmiCalculatorCalculateBmi.setOnClickListener {
            val gender = binding.spFragmentBmiCalculatorGenderSelect.selectedItem.toString()
            val age = binding.etFragmentBmiCalculatorAge.text.toString().toInt()
            val weight = binding.etFragmentBmiCalculatorWeight.text.toString().toDouble()
            val height = binding.etFragmentBmiCalculatorHeight.text.toString().toInt()

            Log.i("Values", gender)
            Log.i("Values", age.toString())
            Log.i("Values", weight.toString())
            Log.i("Values", height.toString())

            val command = BmiCalculatorCommand(gender, age, height, weight)
            viewModel.calculateBmi(command)

            viewModel.mutableCalculateBmiLiveData.observe(viewLifecycleOwner) { response ->
                binding.tvFragmentBmiCalculatorBmiResult.text = "BMI : ${response.bmi}"
                binding.tvFragmentBmiCalculatorBmiInterpretation.text = response.bmiInterpretation
            }
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spFragmentBmiCalculatorGenderSelect.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = BmiCalculatorFragment()
    }
}