package com.school.behealth.search.plans

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.search.plans.dtos.Plan
import com.school.behealth.search.plans.dtos.PlanFilterQuery
import com.school.behealth.search.plans.repositories.IPlanRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class PlanManagerViewModel : ViewModel() {
    val mutablePlanLiveData: MutableLiveData<List<Plan>> = MutableLiveData()
    private val planRepository = RetrofitFactory.instance.create(IPlanRepository::class.java)

    var currentPage = 0
    val pageSize = 10

    fun getPlansFiltered(query: PlanFilterQuery){
        viewModelScope.launch {

            val response = planRepository.getPlansFiltered(query.name, query.category, query.privacy, currentPage, pageSize)
            Log.i("response get plans", "" + response)
            mutablePlanLiveData.postValue(response.plans)
        }
    }

    fun nextPage() {
        currentPage++
        getPlansFiltered(PlanFilterQuery())
    }

    fun previousPage() {
        if (currentPage > 0) {
            currentPage--
            getPlansFiltered(PlanFilterQuery())
        }
    }
}