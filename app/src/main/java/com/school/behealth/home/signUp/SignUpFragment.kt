package com.school.behealth.home.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSignUpBinding
import com.school.behealth.insert.InsertFragment
import com.school.behealth.shared.dtos.session.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.user.create.UserCreateCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        session = SessionManager(requireContext())

        setUpSpinner()
        setOnClickListener()
        observeViewModel()

        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnFragmentSignUpSignUp.setOnClickListener {
            val username = binding.etFragmentSignUpUsername.text.toString()
            val email = binding.etFragmentSignUpMail.text.toString()
            val password = binding.etFragmentSignUpPassword.text.toString()
            val name = binding.etFragmentSignUpName.text.toString()
            val surname = binding.etFragmentSignUpSurname.text.toString()
            val stringBirthdate = binding.etFragmentSignUpBirthdate.text.toString()
            val height = binding.etFragmentSignUpHeight.text.toString().toInt()
            val weight = binding.etFragmentSignUpWeight.text.toString().toInt()
            val gender = binding.spFragmentSignUpGender.selectedItem.toString()

            try {
                val dateFormatInput = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val birthdate: Date = dateFormatInput.parse(stringBirthdate)!!

                val dateFormatOutput =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val formattedBirthdate = dateFormatOutput.format(birthdate)

                val commandCreateUser = UserCreateCommand(
                    mail = email,
                    username = username,
                    birthDate = formattedBirthdate,
                    gender = gender,
                    name = name,
                    surname = surname,
                    password = password,
                    heightInCm = height,
                    weightInG = weight
                )

                session.createUser(commandCreateUser)

                session.mutableCreateUserLiveData.observe(viewLifecycleOwner) { response ->
                    if (response == true)
                        connectApp()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Invalid date, use format dd-MM-yyyy.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun connectApp() {
        val username = binding.etFragmentSignUpUsername.text.toString()
        val password = binding.etFragmentSignUpPassword.text.toString()

        val commandCreateSession = SessionAuthenticateCommand(username, password)

        session.createSession(commandCreateSession)

        session.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
            session.registerPref(response, commandCreateSession.password)
            replaceFragment(InsertFragment())
        }
    }

    private fun observeViewModel() {
        session.mutableLiveSessionData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "Hello, ${response.username} you are connected on the app",
                Toast.LENGTH_LONG
            ).show()
        }

        session.mutableLiveErrorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spFragmentSignUpGender.adapter = adapter
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
        fun newInstance() = SignUpFragment()
    }

}