package com.school.behealth.calculators.nutritionsCalculate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.school.behealth.R
import com.school.behealth.databinding.FragmentEatensFoodsCalculatorBinding
import com.school.behealth.shared.dtos.foodApiCall.CreateFoodApiCallCommand

class EatenFoodsCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentEatensFoodsCalculatorBinding
    private lateinit var viewModel: CalculateEatenFoodsManagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEatensFoodsCalculatorBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[CalculateEatenFoodsManagerViewModel::class.java]

        setOnClickListener()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClickListener() {
        binding.btnFragmentEatenFoodsCalculatorCalculateCaloriesEat.setOnClickListener {
            val nameFood = binding.etFragmentEatenFoodsCalculatorFoodsNames.text.toString()
            val quantity = binding.etFragmentEatenFoodsCalculatorWeight.text.toString().toInt()

            val command = CreateFoodApiCallCommand(nameFood, quantity)
            viewModel.calculateEatenFoodsCalories(command)

            viewModel.mutableLiveCaloriesFoodEatenData.observe(viewLifecycleOwner) { response ->
                binding.tvFragmentEatenFoodsCalculatorFoodsNameResult.text = response.foodsName
                binding.tvFragmentEatenFoodsCalculatorServingsWeightResult.text =
                    response.servingWeights.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorCaloriesResult.text =
                    response.calories.toString() + "kcal"
                binding.tvFragmentEatenFoodsCalculatorTotalFatsResult.text =
                    response.totalFats.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorSaturedFatsResult.text =
                    response.saturatedFats.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorCholesterolResult.text =
                    response.cholesterol.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorSodiumResult.text =
                    response.sodium.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorTotalCarbohydratesResult.text =
                    response.totalCarbohydrates.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorDietaryFiberResult.text =
                    response.totalCarbohydrates.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorSugarsResult.text =
                    response.sugars.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorProteinsResult.text =
                    response.proteins.toString() + "gr"
                binding.tvFragmentEatenFoodsCalculatorPotassiumResult.text =
                    response.potassium.toString() + "gr"

            }
        }
    }

    companion object {
        fun newInstance() = EatenFoodsCalculatorFragment()
    }
}