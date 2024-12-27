package com.school.behealth.insert.height

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentHeightInsertBinding
import com.school.behealth.insert.height.dtos.CreateUserHeightCommand
import com.school.behealth.shared.model.SessionManager

class HeightInsertFragment : Fragment() {
    private lateinit var binding: FragmentHeightInsertBinding
    private lateinit var viewModel: HeightInsertManagerViewModel
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeightInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[HeightInsertManagerViewModel::class.java]

        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserHeightData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), "You add ${response.heightInCm} cm as data height", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpListeners() {
        binding.btnFragmentHeightInsertInsertHeight.setOnClickListener {
            val height = binding.etFragmentHeightInsertHeight.text.toString().toInt()
            val userId = session.getUserId()!!.toInt()

            val command = CreateUserHeightCommand(
                heightInCm = height
            )

            viewModel.insertUserHeight(userId, command)
        }
    }

    companion object {
        fun newInstance() = HeightInsertFragment()
    }
}