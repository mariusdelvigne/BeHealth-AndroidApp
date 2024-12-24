package com.school.behealth.search.plans.repositories

import com.school.behealth.search.plans.dtos.PlanFilterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IPlanRepository {
    @GET("plans")
suspend fun getPlansFiltered(@Query("name") name: String? = null,
                             @Query("category") category: String? = null,
                             @Query("privacy") privacy: String? = null,
                             @Query("pageNumber") pageNumber: Int = 0,
                             @Query("pageSize") pageSize: Int = 10): PlanFilterResponse
}