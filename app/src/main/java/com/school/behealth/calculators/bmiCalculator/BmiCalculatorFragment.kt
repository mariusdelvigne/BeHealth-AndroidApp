package com.school.behealth.calculators.bmiCalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.school.behealth.R
import com.school.behealth.databinding.FragmentBmiCalculatorBinding

class BmiCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentBmiCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmiCalculatorBinding.inflate(layoutInflater, container, false)

        setUpSpinner()
        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.btnFragmentBmiCalculatorCalculateBmi.setOnClickListener {
            val gender = binding.spFragmentBmiCalculatorGenderSelect.selectedItem.toString()
            val age = binding.etFragmentBmiCalculatorAge.text.toString()
            val weight = binding.etFragmentBmiCalculatorWeight.text.toString()
            val height = binding.etFragmentBmiCalculatorHeight.text.toString()

            Log.i("Values", gender)
            Log.i("Values", age)
            Log.i("Values", weight)
            Log.i("Values", height)
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