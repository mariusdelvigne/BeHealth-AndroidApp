package com.school.behealth.search.plans

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.viewModels
import com.school.behealth.R
import com.school.behealth.databinding.FragmentPlanManagerBinding
import com.school.behealth.search.plans.dtos.PlanFilterQuery

class PlanManagerFragment : Fragment() {
    private lateinit var binding: FragmentPlanManagerBinding

    companion object {
        fun newInstance() = PlanManagerFragment()
    }

    private val viewModel: PlanManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val planListFragment = childFragmentManager
            .findFragmentById(R.id.fragmentContainerView_planFragmentManager_planListFragment) as PlanListFragment

        viewModel.mutablePlanLiveData.observe(viewLifecycleOwner) {
            Log.i("Plans", it.toString())

            planListFragment.initUIWithTodoList(it)
        }

        val query = PlanFilterQuery(
            privacy = "public",
        )
        viewModel.getPlansFiltered(query)

        setUpListeners()
        setUpSpinner()
    }

    private fun setUpListeners() {
        binding.btnPlanFragmentManagerFilter.setOnClickListener {
            val name = requireView().findViewById<EditText>(R.id.et_planFragmentManager_titlePlan).text.toString()
            val category = requireView().findViewById<Spinner>(R.id.sp_planFragmentManager_categoryPlan).selectedItem.toString()

            val query = PlanFilterQuery(
                name = name,
                category = category,
                privacy = "public",
            )

            viewModel.getPlansFiltered(query)
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spPlanFragmentManagerCategoryPlan.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanManagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}