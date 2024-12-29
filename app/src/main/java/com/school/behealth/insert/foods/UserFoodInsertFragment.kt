package com.school.behealth.insert.foods

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentUserFoodInsertBinding
import com.school.behealth.insert.foods.dtos.CreateUserFoodCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class UserFoodInsertFragment : Fragment() {
    private lateinit var binding: FragmentUserFoodInsertBinding
    private lateinit var viewModel: UserFoodInsertManagerViewModel
    private lateinit var session: SessionManager
    private var selectedDateTime: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserFoodInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[UserFoodInsertManagerViewModel::class.java]

        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserFoodData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "You added ${response.quantityInG} g as eaten food: ${response.name}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpListeners() {
        binding.etFragmentUserFoodInsertDateTimeEaten.setOnClickListener {
            showDateTimePicker(selectedDateTime){ formattedDateTime ->
                binding.etFragmentUserFoodInsertDateTimeEaten.text = formattedDateTime
            }
        }

        binding.btnFragmentUserFoodInsertInsertUserFood.setOnClickListener {
            val name = binding.etFragmentUserFoodInsertNameFood.text.toString()
            val quantityInG = binding.etFragmentUserFoodInsertQuantity.text.toString().toIntOrNull()

            val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            val dateTimeEaten = isoDateFormat.format(selectedDateTime.time)

            val userId = session.getUserId()!!.toInt()

            if (name.isBlank() || quantityInG == null) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.i("DateTime", dateTimeEaten)

            val command = CreateUserFoodCommand(
                name = name,
                quantityInG = quantityInG,
                eatenDateTime = dateTimeEaten
            )

            viewModel.insertUserFood(userId, command)
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

    companion object {
        fun newInstance() = UserFoodInsertFragment()
    }
}
