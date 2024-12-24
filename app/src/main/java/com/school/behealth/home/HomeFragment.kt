package com.school.behealth.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.school.behealth.R
import com.school.behealth.databinding.FragmentHomeBinding
import com.school.behealth.home.signIn.SignInFragment
import com.school.behealth.home.signUp.SignUpFragment
import com.school.behealth.shared.model.SessionManager
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())

        replaceFragment(SignInFragment())

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frameLayout_mainActivity, fragment, "calculatorFragment")
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}