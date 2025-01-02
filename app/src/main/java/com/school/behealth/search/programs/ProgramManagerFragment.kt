package com.school.behealth.search.programs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.school.behealth.R
import com.school.behealth.databinding.FragmentProgramManagerBinding
import com.school.behealth.search.programs.dtos.ProgramFilterQuery
import com.school.behealth.shared.model.SessionManager

class ProgramManagerFragment : Fragment() {
    private lateinit var binding: FragmentProgramManagerBinding
    lateinit var session: SessionManager

    private val viewModel: ProgramManagerViewModel by viewModels()

    companion object {
        fun newInstance() = ProgramManagerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManager(requireContext())
    }

    override fun onStart() {
        super.onStart()

        val programListFragment = childFragmentManager
            .findFragmentById(R.id.fragmentContainerView_programFragmentManager_programListFragment) as ProgramListFragment

        viewModel.mutableProgramLiveData.observe(viewLifecycleOwner) { programs ->
            programListFragment.initUIWithProgramList(programs)
        }

        viewModel.mutableFavoritesLiveData.observe(viewLifecycleOwner) {
            viewModel.syncFavoritesWithPrograms()
        }

        viewModel.mutableSubscriptionsLiveData.observe(viewLifecycleOwner) {
            viewModel.syncSubscriptionsWithPrograms()
        }

        val userId = session.getUserId()
        val query = ProgramFilterQuery(privacy = "public")

        if (userId != null) {
            viewModel.getProgramsFiltered(query, false, userId)
            viewModel.getAllAssociations("favorite", userId.toInt())
            viewModel.getAllAssociations("subscription", userId.toInt())
        }

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
        val userId = session.getUserId()
        binding.btnProgramFragmentManagerFilter.setOnClickListener {
            viewModel.currentPage = 0

            if (userId != null) {
                viewModel.getProgramsFiltered(getFilterQuery(), true, userId.toInt())
            }
        }

        binding.btnProgramFragmentManagerMore.setOnClickListener {
            if (userId != null) {
                viewModel.more(getFilterQuery(), userId.toInt())
                viewModel.getAllAssociations("favorite", userId.toInt())
                viewModel.getAllAssociations("subscription", userId.toInt())
            }
        }
    }

    fun changeFavorite(programId: Int, isFavorite: Boolean) {
        val userId = session.getUserId() ?: return
        if (isFavorite) {
            viewModel.changeAssociation(userId.toInt(), programId, "favorite", "add")
        } else {
            viewModel.changeAssociation(userId.toInt(), programId, "favorite", "remove")
        }
    }

    fun changeSubscription(programId: Int, isSubscribed: Boolean) {
        val userId = session.getUserId() ?: return
        if (isSubscribed) {
            viewModel.changeAssociation(userId.toInt(), programId, "subscription", "add")
        } else {
            viewModel.changeAssociation(userId.toInt(), programId, "subscription", "remove")
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
