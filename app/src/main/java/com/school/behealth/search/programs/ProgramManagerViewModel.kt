package com.school.behealth.search.programs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.programs.dtos.Association
import com.school.behealth.search.programs.dtos.Program
import com.school.behealth.search.programs.dtos.ProgramFilterQuery
import com.school.behealth.search.programs.repositories.IAssociationRepository
import com.school.behealth.search.programs.repositories.IProgramRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class ProgramManagerViewModel : ViewModel() {
    val mutableProgramLiveData: MutableLiveData<List<Program>> = MutableLiveData()
    val mutableAssociationLiveData: MutableLiveData<List<Association>> = MutableLiveData()
    private val programRepository = RetrofitFactory.instance.create(IProgramRepository::class.java)
    private val associationRepository = RetrofitFactory.instance.create(IAssociationRepository::class.java)

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

    fun getAllAssociations(relationType: String, userId: Int) {
        viewModelScope.launch {
            try {
                val response = associationRepository.getAllAssociations(userId, relationType, currentPage, pageSize)
                mutableAssociationLiveData.postValue(response.astHealthProgramUsers)
            } catch (e: Exception) {
                Log.e("ProgramViewModel", "Error fetching associations", e)
            }
        }
    }


    fun syncFavoritesWithPrograms() {
        val associations = mutableAssociationLiveData.value.orEmpty()
        val programs = mutableProgramLiveData.value.orEmpty().toMutableList()


        Log.d("ProgramViewModel", "associations" + associations)
        programs.forEach { program ->
            Log.d("ProgramViewModel", "program id" + program.id)
            program.isFavorite = associations.any { it.program.id == program.id}
        }
        mutableProgramLiveData.postValue(programs)
    }


    fun more(query: ProgramFilterQuery) {
        currentPage++
        getProgramsFiltered(query)
    }
}