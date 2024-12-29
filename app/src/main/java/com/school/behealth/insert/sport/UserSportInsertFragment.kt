package com.school.behealth.insert.sport

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.databinding.FragmentUserSportInsertBinding
import com.school.behealth.insert.sport.dtos.CreateUserSportCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class UserSportInsertFragment : Fragment() {
    private lateinit var binding: FragmentUserSportInsertBinding
    private lateinit var viewModel: UserSportInsertManagerViewModel
    private lateinit var session: SessionManager
    private var startDateTime: Calendar = Calendar.getInstance()
    private var endDateTime: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserSportInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[UserSportInsertManagerViewModel::class.java]

        setUpSpinner()
        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserSportData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "You added ${response.name} starting at ${response.startDatetime} and ending at ${response.endDatetime}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpListeners() {
        binding.etFragmentUserSportInsertUserSportDateStart.setOnClickListener {
            showDateTimePicker(startDateTime) { formattedDateTime ->
                binding.etFragmentUserSportInsertUserSportDateStart.text = formattedDateTime
            }
        }

        binding.etFragmentUserSportInsertUserSportDateEnd.setOnClickListener {
            showDateTimePicker(endDateTime) { formattedDateTime ->
                binding.etFragmentUserSportInsertUserSportDateEnd.text = formattedDateTime
            }
        }

        binding.btnFragmentUserSportInsertInsertUserSport.setOnClickListener {
            val nameSport = binding.spFragmentUserSportInsertSportSelected.selectedItem.toString()

            val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            val startDateTimeFormatted = isoDateFormat.format(startDateTime.time)
            val endDateTimeFormatted = isoDateFormat.format(endDateTime.time)

            val userId = session.getUserId()!!.toInt()

            if (nameSport.isBlank()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.i("SportDate", startDateTimeFormatted)
            Log.i("SportDate", endDateTimeFormatted)

            val command = CreateUserSportCommand(
                name = nameSport,
                startDateTime = startDateTimeFormatted,
                endDateTime = endDateTimeFormatted
            )

            viewModel.insertUserSport(userId, command)
        }
    }

    private fun showDateTimePicker(calendar: Calendar, onDateTimeSelected: (String) -> Unit) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.SECOND, 0)

                    val isoDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                    isoDateFormat.timeZone = TimeZone.getTimeZone("UTC")
                    val formattedDateTime = isoDateFormat.format(calendar.time)

                    onDateTimeSelected(formattedDateTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sport_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spFragmentUserSportInsertSportSelected.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = UserSportInsertFragment()
    }
}
