package ua.com.foxminded.jetpacknavtest.data.network

import retrofit2.Response
import retrofit2.http.*
import ua.com.foxminded.jetpacknavtest.data.models.DriverInfo
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
        @Header("Cookie") cookie: String
    ): Response<Unit>

    @Headers(value = [
        "Accept: application/json",
        "Accept-Encoding: gzip"
    ])
    @GET("rest/drivers")
    suspend fun getDrivers(
        @Header("Cookie") cookie: String
    ): List<DriverInfo>

}