package com.school.behealth.insert

import SleepInsertFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentInsertBinding
import com.school.behealth.insert.height.HeightInsertFragment
import com.school.behealth.insert.period.PeriodInsertFragment

class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(layoutInflater, container, false)

        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.btnFragmentInsertHeight.setOnClickListener {
            replaceFragment(HeightInsertFragment())
        }

        binding.btnFragmentInsertPeriods.setOnClickListener {
            replaceFragment(PeriodInsertFragment())
        }

        binding.btnFragmentInsertSleeps.setOnClickListener {
            replaceFragment(SleepInsertFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frameLayout_mainActivity, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = InsertFragment()
    }
}