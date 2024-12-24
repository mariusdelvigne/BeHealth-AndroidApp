package com.school.behealth.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentHomeBinding
import com.school.behealth.home.signIn.SignInFragment
import com.school.behealth.home.signUp.SignUpFragment
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

        session.verifyConnection()
        observeConnectionStatus()

        return binding.root
    }

    private fun observeConnectionStatus() {
        session.isConnectedLiveData.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                replaceFragment(SignUpFragment())
            } else {
                replaceFragment(SignInFragment())
            }
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
        fun newInstance() = HomeFragment()
    }
}
