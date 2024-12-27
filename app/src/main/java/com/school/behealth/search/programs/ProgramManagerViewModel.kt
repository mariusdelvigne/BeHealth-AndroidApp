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

    fun getProgramsFiltered(query: ProgramFilterQuery, clearList: Boolean = false) {
        viewModelScope.launch {
            val response = programRepository.getProgramsFiltered(query.title, query.privacy, currentPage, pageSize)

            // Clear the list if clicks on filter
            if (clearList) {
                mutableProgramLiveData.postValue(response.programs)
            }
            // Add new elements to the existing list if click on more
            else {
                val currentList = mutableProgramLiveData.value.orEmpty().toMutableList()
                currentList.addAll(response.programs)
                mutableProgramLiveData.postValue(currentList)
            }
        }
    }

    fun more(query: ProgramFilterQuery) {
        currentPage++
        getProgramsFiltered(query)
    }
}