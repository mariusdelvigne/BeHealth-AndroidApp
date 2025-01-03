package com.school.behealth.search.programs.detailsProgramsFragment.repositories

import com.school.behealth.search.programs.detailsProgramsFragment.dtos.plansDetails.PlansGetByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IPlanDetailsRepository {
    @GET("plans/{planId}")
    suspend fun getPlanById(@Path("planId") planId: Int): PlansGetByIdResponse
}