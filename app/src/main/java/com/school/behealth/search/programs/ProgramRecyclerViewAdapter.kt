package com.school.behealth.search.programs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.school.behealth.R
import com.school.behealth.databinding.FragmentProgramItemBinding
import com.school.behealth.search.programs.detailsProgramsFragment.DetailsProgramsFragment
import com.school.behealth.search.programs.dtos.Program

class ProgramRecyclerViewAdapter(
    private val programs: List<Program>,
    private val fragment: ProgramManagerFragment
) : RecyclerView.Adapter<ProgramRecyclerViewAdapter.ViewHolder>() {

    private var isUserLoggedIn: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProgramItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = programs[position]

        holder.tvTitle.text = program.title

        // Display/Undisplay fav/sub icons if connected or not
        if (isUserLoggedIn) {
            holder.ivFavorite.visibility = View.VISIBLE
            holder.ivSubscription.visibility = View.VISIBLE
            holder.ivFavorite.setImageResource(
                if (program.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_empty
            )
            holder.ivSubscription.setImageResource(
                if (program.isSubscribed) R.drawable.ic_subscription_active else R.drawable.ic_subscription_inactive
            )
        } else {
            holder.ivFavorite.visibility = View.GONE
            holder.ivSubscription.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {

            val fragmentManager = fragment.requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            val currentFragment = fragmentManager.findFragmentByTag("programSearchFragment")
            if (currentFragment != null) {
                transaction.remove(currentFragment)
                Log.i("FragmentTransaction", "Removed programSearchFragment")
            }

            transaction.replace(R.id.frameLayout_mainActivity, DetailsProgramsFragment(), "detailsFragment")
            transaction.addToBackStack(null)
            transaction.commit()
            Log.i("FragmentTransaction", "DetailsProgramsFragment added")
        }

        holder.itemView.setOnLongClickListener {
            if (isUserLoggedIn) {
                showProgramActionDialog(holder.itemView.context, program)
            }
            true
        }
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        this.isUserLoggedIn = isLoggedIn
    }

    private fun showProgramActionDialog(context: Context, program: Program) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_program_relations, null)

        val btnToggleFavorite: Button = dialogView.findViewById(R.id.btn_toggleFavorite)
        val btnToggleSubscription: Button = dialogView.findViewById(R.id.btn_toggleSubscription)
        val btnCancel: Button = dialogView.findViewById(R.id.btn_cancel)

        btnToggleFavorite.text = if (program.isFavorite) "Remove from Favorites" else "Add to Favorites"
        btnToggleSubscription.text = if (program.isSubscribed) "Unsubscribe" else "Subscribe"

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        btnToggleFavorite.setOnClickListener {
            val userId = fragment.session.getUserId() ?: return@setOnClickListener
            fragment.changeFavorite(program.id, !program.isFavorite)
            dialog.dismiss()
        }

        btnToggleSubscription.setOnClickListener {
            val userId = fragment.session.getUserId() ?: return@setOnClickListener
            fragment.changeSubscription(program.id, !program.isSubscribed)
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
