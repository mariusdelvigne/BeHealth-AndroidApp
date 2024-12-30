package com.school.behealth.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSettingsBinding
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Locale

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsUserManagerViewModel
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[SettingsUserManagerViewModel::class.java]
        session = SessionManager(requireContext())

        setDataConnection()

        observableViewModels()
        seOnClickListener()
        return binding.root
    }

    private fun observableViewModels() {
        viewModel.mutableLiveUserInformationDate.observe(viewLifecycleOwner) { response ->
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            binding.etFragmentSettingsInputUsername.text = response.username
            binding.etFragmentSettingsInputMail.text = response.mail
            binding.etFragmentSettingsInputName.text = response.name
            binding.etFragmentSettingsInputSurname.text = response.surname

            val date = outputFormat.parse(response.birthDate)
            val formattedDate = outputFormat.format(date)
            binding.tvFragmentSettingsInputBirthDate.text = formattedDate
            binding.spFragmentSettingsInputGender.text = response.gender
        }
    }

    private fun setDataConnection() {
        val userId: Int? = session.getUserId()

        if (userId != null)
            viewModel.getUserInformation(userId)
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