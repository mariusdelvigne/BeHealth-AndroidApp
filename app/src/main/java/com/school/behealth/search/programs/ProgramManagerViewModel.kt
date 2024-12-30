package com.school.behealth.search.programs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.programs.dtos.Association
import com.school.behealth.search.programs.dtos.AssociationCreateCommand
import com.school.behealth.search.programs.dtos.Program
import com.school.behealth.search.programs.dtos.ProgramFilterQuery
import com.school.behealth.search.programs.repositories.IAssociationRepository
import com.school.behealth.search.programs.repositories.IProgramRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class ProgramManagerViewModel : ViewModel() {
    val mutableProgramLiveData: MutableLiveData<List<Program>> = MutableLiveData()
    val mutableFavoritesLiveData: MutableLiveData<List<Association>> = MutableLiveData()
    val mutableSubscriptionsLiveData: MutableLiveData<List<Association>> = MutableLiveData()

    private val programRepository = RetrofitFactory.instance.create(IProgramRepository::class.java)
    private val associationRepository =
        RetrofitFactory.instance.create(IAssociationRepository::class.java)

    var currentPage = 0
    var pageSize = 10

    fun getProgramsFiltered(query: ProgramFilterQuery, clearList: Boolean = false) {
        viewModelScope.launch {
            val response = programRepository.getProgramsFiltered(
                query.title,
                query.privacy,
                currentPage,
                pageSize
            )

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
            var page = 0
            val accumulatedData = mutableListOf<Association>()

            while (page <= currentPage) {
                val response = associationRepository.getAllAssociations(
                    userId,
                    relationType,
                    page,
                    pageSize
                )

                accumulatedData.addAll(response.astHealthProgramUsers)
                page++
            }

            // Post accumulated data after the loop
            if (relationType == "favorite") {
                mutableFavoritesLiveData.postValue(accumulatedData)
            } else if (relationType == "subscription") {
                mutableSubscriptionsLiveData.postValue(accumulatedData)
            }
        }
    }


    fun changeAssociation(userId: Int, programId: Int, relationType: String, action: String) {
        viewModelScope.launch {
            if (action == "add") {
                val command = AssociationCreateCommand(programId, relationType)
                val response = associationRepository.createAssociation(
                    userId = userId,
                    command = command
                )

            } else if (action == "remove") {
                associationRepository.deleteAssociation(
                    userId = userId,
                    relationType = relationType,
                    programId = programId
                )
            }

            getAllAssociations(relationType, userId)

            if (relationType == "favorite") {
                syncFavoritesWithPrograms()
            } else if (relationType == "subscription") {
                syncSubscriptionsWithPrograms()
            }
        }
    }

    fun syncFavoritesWithPrograms() {
        val favoriteAssociations = mutableFavoritesLiveData.value.orEmpty()
        val programs = mutableProgramLiveData.value.orEmpty().toMutableList()

        programs.forEach { program ->
            program.isFavorite = favoriteAssociations.any { it.program.id == program.id }
        }
        mutableProgramLiveData.postValue(programs)
    }

    fun syncSubscriptionsWithPrograms() {
        val subscriptionAssociations = mutableSubscriptionsLiveData.value.orEmpty()
        val programs = mutableProgramLiveData.value.orEmpty().toMutableList()

        programs.forEach { program ->
            program.isSubscribed = subscriptionAssociations.any { it.program.id == program.id }
        }
        mutableProgramLiveData.postValue(programs)
    }

    fun more(query: ProgramFilterQuery) {
        currentPage++
        getProgramsFiltered(query)
    }
}