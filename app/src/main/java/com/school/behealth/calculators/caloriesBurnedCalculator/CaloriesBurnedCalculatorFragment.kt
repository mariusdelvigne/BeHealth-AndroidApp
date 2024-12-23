package com.school.behealth.calculators.caloriesBurnedCalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.calculators.caloriesBurnedCalculator.dtos.CaloriesBurnedCalculatorCommand
import com.school.behealth.databinding.FragmentCaloriesBurnedCalculatorBinding

class CaloriesBurnedCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCaloriesBurnedCalculatorBinding
    private lateinit var viewModel: CalculateCaloriesBurnedManagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaloriesBurnedCalculatorBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[CalculateCaloriesBurnedManagerViewModel::class.java]

        setUpSpinners()
        setOnClickListener()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClickListener() {
        binding.btnFragmentCaloriesBurnedCalculatorCalculateCaloriesBurned.setOnClickListener {
            val sport = binding.spFragmentCaloriesBurnedCalculatorSportSelected.selectedItem.toString()
            val gender = binding.spFragmentCaloriesBurnedCalculatorGenderSelected.selectedItem.toString()
            val age = binding.etFragmentCaloriesBurnedCalculatorAge.text.toString().toInt()
            val duration = binding.etFragmentCaloriesBurnedCalculatorTime.text.toString().toInt()
            val height = binding.etFragmentCaloriesBurnedCalculatorHeight.text.toString().toInt()
            val weight = binding.etFragmentCaloriesBurnedCalculatorWeight.text.toString().toDouble()

            val command = CaloriesBurnedCalculatorCommand(sport, gender, age, duration, height, weight)
            viewModel.calculateCaloriesBurned(command)

            viewModel.mutableLiveCaloriesBurnedData.observe(viewLifecycleOwner) { response ->
                binding.tvFragmentCaloriesBurnedCalculatorResultSportTime.text =
                    "Sport : ${response.nameSport}, during : ${response.durationMinutes} mins"
                binding.tvFragmentCaloriesBurnedCalculatorResultCalories.text =
                    "Result : ${response.calories} kcal burned"
            }
        }
    }

    private fun setUpSpinners() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sport_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spFragmentCaloriesBurnedCalculatorSportSelected.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spFragmentCaloriesBurnedCalculatorGenderSelected.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = CaloriesBurnedCalculatorFragment()
    }

}