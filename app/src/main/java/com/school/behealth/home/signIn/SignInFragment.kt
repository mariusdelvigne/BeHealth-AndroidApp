package com.school.behealth.home.signIn

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSignInBinding
import com.school.behealth.shared.dtos.SessionAuthenticateCommand

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInManagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[SignInManagerViewModel::class.java]

        setOnClickListener()

        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnFragmentSignInConnection.setOnClickListener {
            val username = binding.etFragmentSignInUsername.text.toString()
            val password = binding.etFragmentSignInPassword.text.toString()

            val command = SessionAuthenticateCommand(username, password)
            viewModel.createSession(command)

            viewModel.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
//                Log.i()
                Toast.makeText(requireContext(), "${response.username} + ${response.tokenExpirationDateTime}", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}