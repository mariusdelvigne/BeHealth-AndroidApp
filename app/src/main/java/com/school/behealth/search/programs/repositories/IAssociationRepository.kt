package com.school.behealth.search.programs.repositories

import com.school.behealth.search.programs.dtos.AssociationCreateCommand
import com.school.behealth.search.programs.dtos.AssociationCreateResponse
import com.school.behealth.search.programs.dtos.AssociationGetAllResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IAssociationRepository {
    @POST("users/{userId}/associations")
    suspend fun createAssociation(
        @Path("userId") userId: Int,
        @Body command: AssociationCreateCommand
    ): AssociationCreateResponse

    @DELETE("users/{userId}/associations/delete")
    suspend fun deleteAssociation(
        @Path("userId") userId: Int,
        @Query("relationType") relationType: String,
        @Query("programId") programId: Int
    ): Response<Unit>

    @GET("users/{userId}/associations/{relationType}")
    suspend fun getAllAssociations(
        @Path("userId") userId: Int,
        @Path("relationType") relationType: String,
        @Query("pageNumber") pageNumber: Int = 0,
        @Query("pageSize") pageSize: Int = 10
    ): AssociationGetAllResponse
}