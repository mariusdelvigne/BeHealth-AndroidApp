package com.school.behealth.search.programs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.behealth.R
import com.school.behealth.search.plans.PlanListFragment
import com.school.behealth.search.programs.dtos.Program

class ProgramListFragment : Fragment() {
    private val programs: ArrayList<Program> = arrayListOf()
    private val programRecyclerViewAdapter = ProgramRecyclerViewAdapter(programs)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_program_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = programRecyclerViewAdapter
            }
        }

        return view
    }

    fun initUIWithProgramList(programs: List<Program>) {
        this.programs.clear()
        programs.forEach { this.programs.add(it) }
        programRecyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = PlanListFragment()
    }
}