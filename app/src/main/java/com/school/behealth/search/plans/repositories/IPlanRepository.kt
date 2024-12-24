package com.school.behealth.search.plans.repositories

import com.school.behealth.search.plans.dtos.PlanFilterQuery
import com.school.behealth.search.plans.dtos.PlanFilterResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IPlanRepository {
    @GET("plans")
    suspend fun getPlansFiltered(@QueryMap command: PlanFilterQuery): List<PlanFilterResponse>
}