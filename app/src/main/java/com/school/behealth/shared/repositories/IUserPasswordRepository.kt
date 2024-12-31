package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.user.updatePassword.UpdatePasswordCommand
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserPasswordRepository {
    @POST("users/{userId}/passwords")
    suspend fun updateUserPassword(@Path("userId") userId: Int, @Body command: UpdatePasswordCommand): Response<Unit>
}