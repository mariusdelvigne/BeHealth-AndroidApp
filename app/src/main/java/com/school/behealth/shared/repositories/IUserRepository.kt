package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.user.create.UserCreateCommand
import com.school.behealth.shared.dtos.user.create.UserCreateResponse
import com.school.behealth.shared.dtos.user.getById.UserGetByIdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserRepository {
    @POST("users")
    suspend fun createUser(@Body command: UserCreateCommand): UserCreateResponse

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): UserGetByIdResponse
}