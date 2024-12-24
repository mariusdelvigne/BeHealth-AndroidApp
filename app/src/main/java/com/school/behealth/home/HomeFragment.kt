package com.school.behealth.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.school.behealth.databinding.FragmentHomeBinding
import com.school.behealth.shared.model.SessionManager

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        session.printToken()

        return binding.root
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}