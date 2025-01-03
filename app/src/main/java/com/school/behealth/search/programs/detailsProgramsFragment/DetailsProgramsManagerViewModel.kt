package com.school.behealth.search.programs.detailsProgramsFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.programs.detailsProgramsFragment.dtos.plansDetails.PlansGetByIdResponse
import com.school.behealth.search.programs.detailsProgramsFragment.dtos.programsDetails.ProgramsGetByIdResponse
import com.school.behealth.search.programs.detailsProgramsFragment.repositories.IPlanDetailsRepository
import com.school.behealth.search.programs.detailsProgramsFragment.repositories.IProgramsDetailsRepository
import com.school.behealth.shared.dtos.user.getById.UserGetByIdResponse
import com.school.behealth.shared.repositories.IUserRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class DetailsProgramsManagerViewModel : ViewModel() {
    val mutableLiveUserInformationData: MutableLiveData<UserGetByIdResponse> = MutableLiveData()
    private val userRepository = RetrofitFactory.instance.create(IUserRepository::class.java)

    val mutableLiveProgramsDetailsData: MutableLiveData<ProgramsGetByIdResponse> = MutableLiveData()
    private val programDetailsRepository =
        RetrofitFactory.instance.create(IProgramsDetailsRepository::class.java)

    val mutableLivePlansDetailsData: MutableLiveData<PlansGetByIdResponse> = MutableLiveData()
    private val planDetailsRepository =
        RetrofitFactory.instance.create(IPlanDetailsRepository::class.java)

    fun getProgramById(programId: Int) {
        viewModelScope.launch {
            try {
                val response = programDetailsRepository.getProgramById(programId)
                mutableLiveProgramsDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("ProgramsResponse", "Error get programs", e)
            }
        }
    }

    fun getPlanById(planId: Int) {
        viewModelScope.launch {
            try {
                val response = planDetailsRepository.getPlanById(planId)
                mutableLivePlansDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("ProgramsResponse", "Error get programs", e)
            }
        }
    }

    fun getUserInformation(userId: Int){
        viewModelScope.launch {
            try {
                val response = userRepository.getUserById(userId)
                mutableLiveUserInformationData.postValue(response)
            } catch (e: Exception) {
                Log.e("UserInformation", "Error get userInfo", e)
            }
        }
    }
}