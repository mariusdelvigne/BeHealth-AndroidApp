package com.school.behealth.search.programs

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.school.behealth.R
import com.school.behealth.databinding.FragmentProgramManagerBinding
import com.school.behealth.search.programs.dtos.ProgramFilterQuery

class ProgramManagerFragment : Fragment() {
    private lateinit var binding: FragmentProgramManagerBinding

    companion object {
        fun newInstance() = ProgramManagerFragment()
    }

    private val viewModel: ProgramManagerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val programListFragment = childFragmentManager
            .findFragmentById(R.id.fragmentContainerView_programFragmentManager_programListFragment) as ProgramListFragment

        viewModel.mutableProgramLiveData.observe(viewLifecycleOwner) {
            programListFragment.initUIWithTodoList(it)
        }

        val query = ProgramFilterQuery(
            privacy = "public",
        )
        viewModel.getProgramsFiltered(query)

        setUpListeners()
    }

    private fun getFilterQuery(): ProgramFilterQuery {
        val title = requireView().findViewById<EditText>(R.id.et_programFragmentManager_titleProgram).text.toString()

        return ProgramFilterQuery(
            title = title,
            privacy = "public"
        )
    }

    private fun setUpListeners() {
        binding.btnProgramFragmentManagerFilter.setOnClickListener {
            val query = getFilterQuery()
            viewModel.currentPage = 0
            viewModel.getProgramsFiltered(query, true)
        }

        binding.btnProgramFragmentManagerMore.setOnClickListener{
            val query = getFilterQuery()
            viewModel.more(query)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramManagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}