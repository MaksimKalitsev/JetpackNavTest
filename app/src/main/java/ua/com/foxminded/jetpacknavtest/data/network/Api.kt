package ua.com.foxminded.jetpacknavtest.data.network

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ua.com.foxminded.jetpacknavtest.data.network.responses.LoginResponse

interface Api {
    @FormUrlEncoded
    @POST("login")
    suspend fun signIn(
        @Field("login") login: String,
        @Field("password") password: String,
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded"
    ): Response<LoginResponse>

    @POST("logout")
    suspend fun logOut(
        @Header("Cookie") cookie: String): Response<Unit>

}