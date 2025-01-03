package com.school.behealth.search.programs.detailsProgramsFragment.repositories

import com.school.behealth.search.programs.detailsProgramsFragment.dtos.programsDetails.ProgramsGetByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IProgramsDetailsRepository {
    @GET("programs/{programId}")
    suspend fun getProgramById(@Path("programId") programId: Int): ProgramsGetByIdResponse
}