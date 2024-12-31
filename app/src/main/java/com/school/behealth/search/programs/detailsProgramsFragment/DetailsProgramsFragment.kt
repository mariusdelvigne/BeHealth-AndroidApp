package com.school.behealth.search.programs.detailsProgramsFragment

import android.os.Bundle
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
        binding = FragmentDetailsProgramsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[DetailsProgramsManagerViewModel::class.java]

        return binding.root
    }

    companion object {
        fun newInstance() = DetailsProgramsFragment()
    }
}