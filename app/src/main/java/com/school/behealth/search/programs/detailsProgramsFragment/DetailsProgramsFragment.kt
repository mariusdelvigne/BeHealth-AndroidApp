package com.school.behealth.search.programs.detailsProgramsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentDetailsProgramsBinding

class DetailsProgramsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsProgramsBinding
    private lateinit var viewModel: DetailsProgramsManagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("DetailsProgramsFragment", "onCreateView called")
        binding = FragmentDetailsProgramsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DetailsProgramsManagerViewModel::class.java]

        setOnclickListeners()

        return binding.root
    }

    private fun setOnclickListeners() {
        Log.i("btn", "btnClicked")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("DetailsProgramsFragment", "onViewCreated called")
        Log.i("DetailsProgramsFragment", "TextView text: ${binding.textView.text}")
    }
}