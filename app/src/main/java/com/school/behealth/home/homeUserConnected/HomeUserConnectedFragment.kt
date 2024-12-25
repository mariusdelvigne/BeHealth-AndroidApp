package com.school.behealth.home.homeUserConnected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentHomeUserConnectedBinding

class HomeUserConnectedFragment : Fragment() {
    private lateinit var binding: FragmentHomeUserConnectedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeUserConnectedBinding.inflate(layoutInflater, container, false)

        return  binding.root
    }

    companion object {
        fun newInstance() = HomeUserConnectedFragment()
    }
}