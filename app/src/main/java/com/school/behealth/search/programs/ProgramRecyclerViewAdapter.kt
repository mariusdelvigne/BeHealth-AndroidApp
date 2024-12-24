package com.school.behealth.search.programs

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.school.behealth.databinding.FragmentProgramItemBinding
import com.school.behealth.search.programs.dtos.Program

class ProgramRecyclerViewAdapter(
    private val values: List<Program>
) : RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProgramItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = values[position]
        holder.tvTitle.text = program.title
        holder.tvCreatorName.text = program.creatorName
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProgramItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvFragmentProgramItemTitle
        val tvCreatorName: TextView = binding.tvFragmentProgramItemCreatorName
    }
}