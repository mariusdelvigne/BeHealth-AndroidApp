package com.school.behealth.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.school.behealth.databinding.FragmentProfileBinding
import com.school.behealth.shared.model.SessionManager

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        session = SessionManager(requireContext())

        seOnClickListener()
        return binding.root
    }

    private fun seOnClickListener() {
        binding.btnFragmentProfileDisconnect.setOnClickListener {
            Toast.makeText(requireContext(), "You have been disconnected", Toast.LENGTH_LONG).show()
            session.disconnect()
            session.deletePref()
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}