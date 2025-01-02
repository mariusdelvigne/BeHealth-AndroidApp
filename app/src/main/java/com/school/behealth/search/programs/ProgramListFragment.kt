package com.school.behealth.search.programs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.search.programs.dtos.Program
import android.view.LayoutInflater

class ProgramListFragment : Fragment() {
    private val programs: ArrayList<Program> = arrayListOf()
    private lateinit var programRecyclerViewAdapter: ProgramRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_program_list, container, false)

        val programManagerFragment = parentFragment as? ProgramManagerFragment
        if (programManagerFragment != null) {
            programRecyclerViewAdapter = ProgramRecyclerViewAdapter(programs, programManagerFragment)
        }

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = programRecyclerViewAdapter
            }
        }

        return view
    }

    fun initUIWithProgramList(programs: List<Program>, isUserLoggedIn: Boolean) {
        this.programs.clear()
        programs.forEach { this.programs.add(it) }
        programRecyclerViewAdapter.setUserLoggedIn(isUserLoggedIn)
        programRecyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = ProgramListFragment()
    }
}

