package ua.com.foxminded.jetpacknavtest.data.network.repository

import ua.com.foxminded.jetpacknavtest.data.models.DriverInfo
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo
import ua.com.foxminded.jetpacknavtest.data.network.Api
import kotlin.Exception

interface ILoginRepository {
    suspend fun login(username: String, password: String): Result<LoginInfo>
    suspend fun logout(): Result<Unit>
    suspend fun currentDriver(email: String, cookie: String): Result<DriverInfo>
}

class LoginRepository(
    private val api: Api
) : ILoginRepository {

    override suspend fun login(username: String, password: String): Result<LoginInfo> {
        try {
            val responseServer = api.signIn(username, password)
            val cookie = responseServer.headers().get("set-cookie")
                ?: return Result.failure(IllegalStateException("absent cookie"))

            return responseServer.body()
                ?.toLoginInfo(password, cookie)
                ?.let { Result.success(it) }
                ?: Result.failure(IllegalStateException("Login response body is absent"))
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    override suspend fun logout(): Result<Unit> {
        val responseServer = api.logOut("Cookie")
        val result = responseServer.headers()["set-cookie"]
        TODO("Not yet implemented")
    }

    override suspend fun currentDriver(email: String, cookie: String): Result<DriverInfo> {
        return try {
            val responseServer = api.getDrivers(cookie)
            val result = responseServer.filter { it.email == email }
            Result.success(result.first())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }


}
