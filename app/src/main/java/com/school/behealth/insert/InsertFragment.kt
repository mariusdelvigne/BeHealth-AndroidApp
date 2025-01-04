package com.school.behealth.insert

import SleepInsertFragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentInsertBinding
import com.school.behealth.insert.foods.UserFoodInsertFragment
import com.school.behealth.insert.height.HeightInsertFragment
import com.school.behealth.insert.period.PeriodInsertFragment
import com.school.behealth.insert.sport.UserSportInsertFragment
import com.school.behealth.insert.weight.WeightInsertFragment

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

        binding.btnFragmentInsertWeight.setOnClickListener {
            replaceFragment(WeightInsertFragment())
        }

        binding.btnFragmentInsertPeriods.setOnClickListener {
            replaceFragment(PeriodInsertFragment())
        }

        binding.btnFragmentInsertFoods.setOnClickListener {
            replaceFragment(UserFoodInsertFragment())
        }

        binding.btnFragmentInsertSleeps.setOnClickListener {
            replaceFragment(SleepInsertFragment())
        }

        binding.btnFragmentInsertSport.setOnClickListener {
            replaceFragment(UserSportInsertFragment())
        }

        binding.tvInsertFragmentLinkGoToSite.setOnClickListener {
            goToWebsite()
        }
    }

    private fun goToWebsite() {
        val url = "http://10.0.2.2:5114/swagger/index.html"
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            startActivity(this)
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