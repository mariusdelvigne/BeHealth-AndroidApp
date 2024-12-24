package com.school.behealth.search.programs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.programs.dtos.Program
import com.school.behealth.search.programs.dtos.ProgramFilterQuery
import com.school.behealth.search.programs.repositories.IProgramRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class ProgramManagerViewModel : ViewModel() {
    val mutableProgramLiveData: MutableLiveData<List<Program>> = MutableLiveData()
    private val programRepository = RetrofitFactory.instance.create(IProgramRepository::class.java)

    var currentPage = 0
    var pageSize = 10

    fun getProgramsFiltered(query: ProgramFilterQuery){
        viewModelScope.launch {

            val response = programRepository.getProgramsFiltered(query.title, query.privacy, currentPage, pageSize)
            mutableProgramLiveData.postValue(response.programs)
        }
    }

    fun nextPage(query: ProgramFilterQuery) {
        currentPage++
        getProgramsFiltered(query)
    }

    fun previousPage(query: ProgramFilterQuery) {
        if (currentPage > 0) {
            currentPage--
            getProgramsFiltered(query)
        }
    }
}