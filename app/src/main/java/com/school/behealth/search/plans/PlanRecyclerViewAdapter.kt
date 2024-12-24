package com.school.behealth.search.plans

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.school.behealth.databinding.FragmentPlanItemBinding
import com.school.behealth.search.plans.dtos.Plan

class PlanRecyclerViewAdapter(
    private val values: List<Plan>
) : RecyclerView.Adapter<PlanRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPlanItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan = values[position]
        holder.tvCategory.text = plan.category
        holder.tvDurationInDays.text = plan.durationInDays.toString()
        holder.tvName.text = plan.name
        Log.i("plans", plan.toString())
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvCategory: TextView = binding.tvFragmentPlanSearchItemCategory
        val tvDurationInDays: TextView = binding.tvFragmentPlanSearchItemDurationInDays
        val tvName: TextView = binding.tvFragmentPlanSearchItemName
    }

}