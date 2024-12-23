package com.school.behealth.calculators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.calculators.bmiCalculator.BmiCalculatorFragment
import com.school.behealth.calculators.caloriesBurnedCalculator.CaloriesBurnedCalculatorFragment
import com.school.behealth.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.btnFragmentCalculatorGoToBmiCalculator.setOnClickListener {
            replaceFragment(BmiCalculatorFragment())
        }
        binding.btnFragmentCalculatorGoToCaloriesBurnedCalculator.setOnClickListener {
            replaceFragment(CaloriesBurnedCalculatorFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frameLayout_mainActivity, fragment, "calculatorFragment")
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = CalculatorFragment()
    }
}