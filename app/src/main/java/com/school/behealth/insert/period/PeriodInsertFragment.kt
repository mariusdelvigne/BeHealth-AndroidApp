package com.school.behealth.insert.period

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentPeriodInsertBinding
import com.school.behealth.insert.period.dtos.CreateUserPeriodCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.*

class PeriodInsertFragment : Fragment() {
    private lateinit var binding: FragmentPeriodInsertBinding
    private lateinit var viewModel: PeriodInsertManagerViewModel
    private lateinit var session: SessionManager
    private val calendar = Calendar.getInstance()
    private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeriodInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[PeriodInsertManagerViewModel::class.java]

        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserPeriodData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "You added ${response.startDate} to ${response.endDate} as data period",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpListeners() {
        binding.etFragmentPeriodInsertStartDate.setOnClickListener {
            showDateTimePickerDialog(binding.etFragmentPeriodInsertStartDate)
        }

        binding.etFragmentPeriodInsertEndDate.setOnClickListener {
            showDateTimePickerDialog(binding.etFragmentPeriodInsertEndDate)
        }

        binding.btnFragmentHeightInsertInsertPeriod.setOnClickListener {
            val startDate = binding.etFragmentPeriodInsertStartDate.text.toString()
            val endDate = binding.etFragmentPeriodInsertEndDate.text.toString()
            val userId = session.getUserId()?.toIntOrNull()

            if (userId != null && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                val command = CreateUserPeriodCommand(
                    startDate = startDate,
                    endDate = endDate
                )

                viewModel.insertUserPeriod(userId, command)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDateTimePickerDialog(editText: View) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            val currentTime = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY))
            calendar.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE))
            calendar.set(Calendar.SECOND, 0)

            (editText as? android.widget.EditText)?.setText(dateTimeFormat.format(calendar.time))
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    companion object {
        fun newInstance() = PeriodInsertFragment()
    }
}
