package com.school.behealth.search.plans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.school.behealth.R
import com.school.behealth.search.plans.dtos.Plan

class PlanListFragment : Fragment() {
    private val plans: ArrayList<Plan> = arrayListOf()
    private val planRecyclerViewAdapter = PlanRecyclerViewAdapter(plans)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_plan_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = planRecyclerViewAdapter
            }
        }
        return view
    }

    fun initUIWithPlanList(plans: List<Plan>) {
        this.plans.clear()
        plans.forEach { this.plans.add(it) }
        planRecyclerViewAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = PlanListFragment()
    }
}