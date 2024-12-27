package com.school.behealth.home.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSignInBinding
import com.school.behealth.insert.InsertFragment
import com.school.behealth.shared.dtos.session.SessionAuthenticateCommand
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

            val command = SessionAuthenticateCommand(
                username = username,
                password = password
            )

            session.createSession(command)

            session.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
                session.registerPref(response, command.password)
                replaceFragment(InsertFragment())
            }
        }
    }

    private fun observeViewModel() {
        session.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), "Hello, ${response.username} you are connected on the app", Toast.LENGTH_LONG).show()
        }

        session.mutableLiveErrorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
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
        fun newInstance() = SignInFragment()
    }
}