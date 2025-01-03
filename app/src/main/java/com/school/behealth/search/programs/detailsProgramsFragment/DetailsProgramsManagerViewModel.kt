package com.school.behealth.search.programs.detailsProgramsFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.programs.dtos.Program
import com.school.behealth.search.programs.dtos.ProgramsGetByIdResponse
import com.school.behealth.search.programs.repositories.IProgramRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class DetailsProgramsManagerViewModel : ViewModel() {
    val mutableLiveProgramsDetailsData: MutableLiveData<ProgramsGetByIdResponse> = MutableLiveData()
    private val programRepository = RetrofitFactory.instance.create(IProgramRepository::class.java)

    fun getProgramById(programId: Int){
        viewModelScope.launch {
            try {
                val response = programRepository.getProgramById(programId)
                mutableLiveProgramsDetailsData.postValue(response)
            } catch (e: Exception) {
                Log.e("ProgramsResponse", "Error get programs", e)
            }
        }
    }
}