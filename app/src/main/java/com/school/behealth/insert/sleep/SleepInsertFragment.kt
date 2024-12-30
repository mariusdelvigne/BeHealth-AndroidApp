import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.databinding.FragmentSleepInsertBinding
import com.school.behealth.insert.sleep.SleepInsertManagerViewModel
import com.school.behealth.insert.sleep.dtos.CreateUserSleepCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class SleepInsertFragment : Fragment() {
    private lateinit var binding: FragmentSleepInsertBinding
    private lateinit var viewModel: SleepInsertManagerViewModel
    private lateinit var session: SessionManager
    private var startDateTime: Calendar = Calendar.getInstance()
    private var endDateTime: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSleepInsertBinding.inflate(layoutInflater, container, false)

        session = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[SleepInsertManagerViewModel::class.java]

        setUpListeners()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.mutableLiveUserSleepData.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "You added ${response.startDatetime} to ${response.endDatetime} as data Sleep",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpListeners() {
        binding.etFragmentSleepInsertStartDate.setOnClickListener {
            showDateTimePicker(startDateTime) { formattedDateTime ->
                binding.etFragmentSleepInsertStartDate.text = formattedDateTime
            }
        }

        binding.etFragmentSleepInsertEndDate.setOnClickListener {
            showDateTimePicker(endDateTime) {formattedDateTime ->
                binding.etFragmentSleepInsertEndDate.text = formattedDateTime
            }
        }

        binding.btnFragmentSleepInsertInsertSleep.setOnClickListener {
            val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            val startDateTimeFormatted = isoDateFormat.format(startDateTime.time)
            val endDateTimeFormatted = isoDateFormat.format(endDateTime.time)

            val userId = session.getUserId()

            if (userId != null && startDateTimeFormatted.isNotEmpty() && startDateTimeFormatted.isNotEmpty()) {
                val command = CreateUserSleepCommand(
                    startDatetime = startDateTimeFormatted,
                    endDatetime = endDateTimeFormatted
                )

                viewModel.insertUserSleep(userId, command)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
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
        fun newInstance() = SleepInsertFragment()
    }
}
