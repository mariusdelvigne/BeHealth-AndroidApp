package com.school.behealth.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSearchBinding
import com.school.behealth.search.plans.PlanManagerFragment
import com.school.behealth.search.programs.ProgramManagerFragment

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.btnSearchFragmentGoToPlans.setOnClickListener {
            replaceFragment(PlanManagerFragment(), "planSearchFragment")
        }

        binding.btnSearchFragmentGoToPrograms.setOnClickListener {
            replaceFragment(ProgramManagerFragment(), "programSearchFragment")
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frameLayout_mainActivity, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}