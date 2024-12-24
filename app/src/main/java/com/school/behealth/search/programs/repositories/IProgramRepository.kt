package com.school.behealth.search.programs.repositories

import com.school.behealth.search.programs.dtos.ProgramFilterQuery
import com.school.behealth.search.programs.dtos.ProgramFilterResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IProgramRepository {
    @GET("programs")
    suspend fun getProgramFiltered(@QueryMap command: ProgramFilterQuery): ProgramFilterResponse
}