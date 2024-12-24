package com.school.behealth.home.signIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.school.behealth.databinding.FragmentSignInBinding
import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.model.SessionManager

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())

        setOnClickListener()
        observeViewModel()

        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnFragmentSignInConnection.setOnClickListener {
            val username = binding.etFragmentSignInUsername.text.toString()
            val password = binding.etFragmentSignInPassword.text.toString()

            val command = SessionAuthenticateCommand(username, password)
            session.createSession(command)
        }
        binding.btnFragmentHomeIsConnected.setOnClickListener {
            session.verifyConnection()
        }
    }

    private fun observeViewModel() {
        session.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), "${response.username} + ${response.tokenExpirationDateTime}", Toast.LENGTH_LONG).show()
        }

        session.mutableLiveErrorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        session.isConnectedLiveData.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                Toast.makeText(requireContext(), "Connected", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Not connected", Toast.LENGTH_LONG).show()
            }
            Log.i("SessionManager", isConnected.toString())
        }
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}