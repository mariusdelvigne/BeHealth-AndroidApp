package com.school.behealth.insert.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentWeightInsertBinding
import com.school.behealth.insert.weight.dtos.CreateUserWeightCommand
import com.school.behealth.shared.model.SessionManager

class WeightInsertFragment : Fragment() {
    private lateinit var binding: FragmentWeightInsertBinding
    private lateinit var viewModel: WeightInsertManagerViewModel
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeightInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[WeightInsertManagerViewModel::class.java]

        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserWeightData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), "You add ${response.weightInG} g as data weight", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpListeners() {
        //TODO PEUT ETRE CHANGER G EN KG ?
        binding.btnFragmentWeightInsertInsertWeight.setOnClickListener {
            val weight = binding.etFragmentWeightInsertWeight.text.toString().toInt()
            val userId = session.getUserId()!!.toInt()

            val command = CreateUserWeightCommand(
                weightInG = weight
            )

            viewModel.insertUserWeight(userId, command)
        }
    }

    companion object {
        fun newInstance() = WeightInsertFragment()
    }

}