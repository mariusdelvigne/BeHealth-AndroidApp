package com.school.behealth.search.programs.repositories

import com.school.behealth.search.programs.dtos.ProgramFilterResponse
import com.school.behealth.search.programs.dtos.ProgramsGetByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IProgramRepository {
    @GET("programs")
    suspend fun getProgramsFiltered(@Query("title") title: String? = null,
                                   @Query("privacy") privacy: String? = null,
                                   @Query("pageNumber") pageNumber: Int = 0,
                                   @Query("pageSize") pageSize: Int = 10): ProgramFilterResponse

    @GET("programs/{programId}")
    suspend fun getProgramById(@Path("programId") programId: Int): ProgramsGetByIdResponse
}