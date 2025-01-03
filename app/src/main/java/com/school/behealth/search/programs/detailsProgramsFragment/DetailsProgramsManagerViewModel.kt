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

    val mutableLiveSportPlanDetailsData: MutableLiveData<PlansGetByIdResponse> = MutableLiveData()
    val mutableLiveFoodPlanDetailsData: MutableLiveData<PlansGetByIdResponse> = MutableLiveData()
    val mutableLiveSleepPlanDetailsData: MutableLiveData<PlansGetByIdResponse> = MutableLiveData()
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

    fun getSportPlanById(planId: Int) {
        viewModelScope.launch {
            try {
                val response = planDetailsRepository.getPlanById(planId)
                mutableLiveSportPlanDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("PlanResponse", "Error fetching sport plan", e)
            }
        }
    }

    fun getFoodPlanById(planId: Int) {
        viewModelScope.launch {
            try {
                val response = planDetailsRepository.getPlanById(planId)
                mutableLiveFoodPlanDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("PlanResponse", "Error fetching food plan", e)
            }
        }
    }

    fun getSleepPlanById(planId: Int) {
        viewModelScope.launch {
            try {
                val response = planDetailsRepository.getPlanById(planId)
                mutableLiveSleepPlanDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("PlanResponse", "Error fetching sleep plan", e)
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