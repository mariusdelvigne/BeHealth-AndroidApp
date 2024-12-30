package com.school.behealth.search.programs

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.school.behealth.R
import com.school.behealth.databinding.FragmentProgramItemBinding
import com.school.behealth.search.programs.dtos.Program

class ProgramRecyclerViewAdapter(
    private val values: List<Program>,
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
        holder.tvDescription.text = program.description

        holder.itemView.setOnClickListener {
            if (holder.tvDescription.visibility == View.GONE) {
                holder.tvDescription.visibility = View.VISIBLE
            } else {
                holder.tvDescription.visibility = View.GONE
            }
        }

        if (program.isFavorite) {
            holder.ivFavorite.setImageResource(R.drawable.ic_star_filled)
        } else {
            holder.ivFavorite.setImageResource(R.drawable.ic_star_empty)
        }

        if (program.isSubscribed) {
            holder.ivSubscription.setImageResource(R.drawable.ic_subscription_active)
        } else {
            holder.ivSubscription.setImageResource(R.drawable.ic_subscription_inactive)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProgramItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvFragmentProgramItemTitle
        val tvCreatorName: TextView = binding.tvFragmentProgramItemCreatorName
        val tvDescription: TextView = binding.tvFragmentProgramItemDescription
        val ivFavorite: ImageView = binding.ivFragmentProgramItemFavorite
        val ivSubscription: ImageView = binding.ivFragmentProgramItemSubscription
    }
}