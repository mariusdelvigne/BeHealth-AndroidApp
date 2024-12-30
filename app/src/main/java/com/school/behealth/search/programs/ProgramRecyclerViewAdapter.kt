package com.school.behealth.search.programs

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.school.behealth.R
import com.school.behealth.databinding.FragmentProgramItemBinding
import com.school.behealth.search.programs.dtos.Program

class ProgramRecyclerViewAdapter(
    private val programs: List<Program>,
    private val fragment: ProgramManagerFragment
) : RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProgramItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = programs[position]

        holder.tvTitle.text = program.title

        // Change the icons properly if favorite or not and subscribed or not
        holder.ivFavorite.setImageResource(
            if (program.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_empty
        )
        holder.ivSubscription.setImageResource(
            if (program.isSubscribed) R.drawable.ic_subscription_active else R.drawable.ic_subscription_inactive
        )

        holder.itemView.setOnLongClickListener {
            showProgramActionDialog(holder.itemView.context, program)
            true
        }
    }

    private fun showProgramActionDialog(context: Context, program: Program) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_program_dialog, null)

        val btnToggleFavorite: Button = dialogView.findViewById(R.id.btn_toggleFavorite)
        val btnToggleSubscription: Button = dialogView.findViewById(R.id.btn_toggleSubscription)
        val btnCancel: Button = dialogView.findViewById(R.id.btn_cancel)

        if (program.isFavorite) {
            btnToggleFavorite.text = "Remove from Favorites"
        } else {
            btnToggleFavorite.text = "Add to Favorites"
        }

        if (program.isSubscribed) {
            btnToggleSubscription.text = "Unsubscribe"
        } else {
            btnToggleSubscription.text = "Subscribe"
        }

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        btnToggleFavorite.setOnClickListener {
            val userId = fragment.session.getUserId() ?: return@setOnClickListener
            if (program.isFavorite) {
                // Remove favorite
                fragment.changeFavorite(program.id, false)
            } else {
                // Add favorite
                fragment.changeFavorite(program.id, true)
            }
            dialog.dismiss()
        }

        btnToggleSubscription.setOnClickListener {
            val userId = fragment.session.getUserId() ?: return@setOnClickListener

            if (program.isSubscribed) {
                // Unsubscribe
                fragment.changeSubscription(program.id, false)
            } else {
                // Subscribe
                fragment.changeSubscription(program.id, true)
            }
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun getItemCount(): Int = programs.size

    inner class ViewHolder(binding: FragmentProgramItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvFragmentProgramItemTitle
        val ivFavorite: ImageView = binding.ivFragmentProgramItemFavorite
        val ivSubscription: ImageView = binding.ivFragmentProgramItemSubscription
    }
}