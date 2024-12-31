package com.school.behealth.settings

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.school.behealth.R
import com.school.behealth.databinding.FragmentSettingsBinding
import com.school.behealth.shared.dtos.session.SessionDataResponse
import com.school.behealth.shared.dtos.user.update.UpdateUserCommand
import com.school.behealth.shared.dtos.user.updatePassword.UpdatePasswordCommand
import com.school.behealth.shared.model.SessionManager
import java.text.SimpleDateFormat
import java.util.Locale

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsUserManagerViewModel
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[SettingsUserManagerViewModel::class.java]
        session = SessionManager(requireContext())

        session.verifyConnection()
        userIsConnected()

        return binding.root
    }

    private fun userIsConnected() {
        session.isConnectedLiveData.observe(viewLifecycleOwner) { response ->
            if (response) {
                binding.linearLayoutFragmentSettingsModifyInformationLayout.visibility =
                    View.VISIBLE
                setDataConnection()
                observableViewModels()
                seOnClickListener()
            } else {
                printUserInformationNotConnected()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun printUserInformationNotConnected() {
        binding.tvInsertFragmentTitleModify.text =
            "You are not connected to the app, please connect you access at this part"
        binding.linearLayoutFragmentSettingsModifyInformationLayout.visibility = View.GONE
    }

    private fun observableViewModels() {
        viewModel.mutableLiveUserInformationDate.observe(viewLifecycleOwner) { response ->
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            binding.etFragmentSettingsInputUsername.text = response.username
            binding.etFragmentSettingsInputMail.text = response.mail
            binding.etFragmentSettingsInputName.text = response.name
            binding.etFragmentSettingsInputSurname.text = response.surname

            val date = outputFormat.parse(response.birthDate)
            val formattedDate = outputFormat.format(date)
            binding.tvFragmentSettingsInputBirthDate.text = formattedDate
            binding.spFragmentSettingsInputGender.text = response.gender
        }
    }

    private fun setDataConnection() {
        val userId: Int? = session.getUserId()

        if (userId != null)
            viewModel.getUserInformation(userId)
    }

    private fun seOnClickListener() {
        binding.btnFragmentProfileDisconnect.setOnClickListener {
            Toast.makeText(requireContext(), "You have been disconnected", Toast.LENGTH_LONG).show()
            session.disconnect()
            session.deletePref()
        }

        binding.btnFragmentSettingsModifyUserInfo.setOnClickListener {
            showModifyUserInfoDialog()
        }

        binding.btnFragmentSettingsModifyUserPassword.setOnClickListener {
            showModifyUserPasswordDialog()
        }
    }

    private fun showModifyUserPasswordDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_user_update_password, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val etCurrentPassword =
            dialogView.findViewById<EditText>(R.id.et_modifyPassword_currentPassword)
        val etNewPassword = dialogView.findViewById<EditText>(R.id.et_modifyPassword_newPassword)
        val etConfirmNewPassword = dialogView.findViewById<EditText>(R.id.et_modifyPassword_confirmNewPassword)
        val btnSaveChanges = dialogView.findViewById<Button>(R.id.btn_modifyPassword_saveChanges)
        val btnCancelChanges =
            dialogView.findViewById<Button>(R.id.btn_modifyPassword_cancelChanges)

        val userId: Int? = session.getUserId()

        if (userId == null) {
            alertDialog.dismiss()
        } else {
            btnSaveChanges.setOnClickListener {

                if (etNewPassword.text.toString() != etConfirmNewPassword.text.toString()) {
                    Toast.makeText(
                        requireContext(),
                        "The new password and the confirm password are not the same",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }

                val command = UpdatePasswordCommand(
                    currentPassword = etCurrentPassword.text.toString(),
                    newPassword = etNewPassword.text.toString()
                )

                viewModel.updateUserPassword(userId, command)
                viewModel.mutableLiveUserPasswordErrorData.observe(viewLifecycleOwner) { response ->
                    Toast.makeText(requireContext(), response, Toast.LENGTH_LONG).show()
                }

                val username = session.getUsername()!!
                val role = session.getRole()!!
                val tokenExpirationDate = java.util.Date()

                val sessionCommand = SessionDataResponse(
                    id = userId,
                    username = username,
                    role = role,
                    tokenExpirationDateTime = tokenExpirationDate
                )

                session.registerPref(sessionCommand, etNewPassword.text.toString())

                alertDialog.dismiss()
            }
        }

        btnCancelChanges.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun showModifyUserInfoDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_user_update_information, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val etModifyUsername = dialogView.findViewById<EditText>(R.id.et_modifyUsername)
        val etModifyMail = dialogView.findViewById<EditText>(R.id.et_modifyMail)
        val etModifyName = dialogView.findViewById<EditText>(R.id.et_modifyName)
        val etModifySurname = dialogView.findViewById<EditText>(R.id.et_modifySurname)
        val etModifyBirthdate = dialogView.findViewById<EditText>(R.id.et_modifyBirthdate)
        val spModifyGender = dialogView.findViewById<Spinner>(R.id.sp_modifyGender)
        val btnSaveChanges = dialogView.findViewById<Button>(R.id.btn_saveChanges)
        val btnCancelChanges = dialogView.findViewById<Button>(R.id.btn_cancelChanges)

        etModifyUsername.setText(binding.etFragmentSettingsInputUsername.text)
        etModifyMail.setText(binding.etFragmentSettingsInputMail.text)
        etModifyName.setText(binding.etFragmentSettingsInputName.text)
        etModifySurname.setText(binding.etFragmentSettingsInputSurname.text)
        etModifyBirthdate.setText(binding.tvFragmentSettingsInputBirthDate.text)

        val genderAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spModifyGender.adapter = genderAdapter
        spModifyGender.setSelection(genderAdapter.getPosition(binding.spFragmentSettingsInputGender.text.toString()))

        val userId: Int? = session.getUserId()

        if (userId == null) {
            alertDialog.dismiss()
        } else {
            btnSaveChanges.setOnClickListener {
                val command = UpdateUserCommand(
                    username = etModifyUsername.text.toString(),
                    mail = etModifyMail.text.toString(),
                    name = etModifyName.text.toString(),
                    surname = etModifySurname.text.toString(),
                    birthDate = etModifyBirthdate.text.toString(),
                    gender = spModifyGender.selectedItem.toString()
                )
                viewModel.updateUserInformation(userId, command)
                viewModel.mutableLiveResponseUserInformationData.observe(viewLifecycleOwner) { response ->
                    Toast.makeText(requireContext(), response, Toast.LENGTH_LONG).show()
                }

                val role = session.getRole()!!
                val tokenExpirationDate = java.util.Date()
                val password = session.getPassword()!!

                val sessionCommand = SessionDataResponse(
                    id = userId,
                    username = etModifyUsername.text.toString(),
                    role = role,
                    tokenExpirationDateTime = tokenExpirationDate
                )

                session.registerPref(sessionCommand, password)

                alertDialog.dismiss()
                viewModel.getUserInformation(userId)
            }
        }

        btnCancelChanges.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}