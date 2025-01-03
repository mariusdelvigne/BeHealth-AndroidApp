package com.school.behealth.search.programs.detailsProgramsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentDetailsProgramsBinding

class DetailsProgramsFragment(programId: Int) : Fragment() {
    private lateinit var binding: FragmentDetailsProgramsBinding
    private lateinit var viewModel: DetailsProgramsManagerViewModel
    private var programId: Int = programId

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsProgramsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DetailsProgramsManagerViewModel::class.java]

        viewModel.getProgramById(programId)

        observeMutableLiveData()

        return binding.root
    }

    private fun observePlanDetails(planId: Int) {
        viewModel.getPlanById(planId)
        viewModel.mutableLivePlansDetailsData.observe(viewLifecycleOwner) { responsePlan ->
            Log.i("responsePlanId", responsePlan.name)
            Log.i("responsePlanId", responsePlan.description)
            Log.i("responsePlanId", responsePlan.creationDatetime)
            Log.i("responsePlanId", responsePlan.category)
        }
    }

    private fun observeMutableLiveData() {
        viewModel.mutableLiveProgramsDetailsData.observe(viewLifecycleOwner) { response ->
            Log.i("responseProgramId", response.title)
            Log.i("responseProgramId", response.description)
            Log.i("responseProgramId", response.privacy)
            Log.i("responseProgramId", response.sleepPlanId.toString())
            Log.i("responseProgramId", response.sportPlanId.toString())
            Log.i("responseProgramId", response.foodPlanId.toString())

            val sportPlanId = response.sportPlanId
            val foodPlanId = response.foodPlanId
            val sleepPlanId = response.sleepPlanId

            response.sleepPlanId?.let {
                if (sleepPlanId == null)
                    return@observe
                observePlanDetails(sleepPlanId)
            }
            response.foodPlanId?.let {
                if (foodPlanId == null)
                    return@observe
                observePlanDetails(foodPlanId)
            }
            response.sportPlanId?.let {
                if (sportPlanId == null)
                    return@observe
                observePlanDetails(sportPlanId)
            }
        }
    }

}