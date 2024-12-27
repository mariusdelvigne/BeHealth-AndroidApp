package com.school.behealth.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSettingsBinding
import com.school.behealth.shared.model.SessionManager

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
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
        fun newInstance() = SettingsFragment()
    }
}