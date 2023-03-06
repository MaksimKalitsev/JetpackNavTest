package ua.com.foxminded.jetpacknavtest.data.network.repository

import ua.com.foxminded.jetpacknavtest.data.models.DriverInfo
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo
import ua.com.foxminded.jetpacknavtest.data.network.Api
import kotlin.Exception

interface ILoginRepository {
    suspend fun login(username: String, password: String): Result<LoginInfo>
    suspend fun logout(cookie: String): Result<Unit>
    suspend fun currentDriver(email: String, cookie: String): Result<DriverInfo>
}

class LoginRepository(
    private val api: Api
) : ILoginRepository {

    override suspend fun login(username: String, password: String): Result<LoginInfo> {
        try {
            val responseServer = api.signIn(username, password)
            val cookie = responseServer.headers()["set-cookie"]
                ?: return Result.failure(IllegalStateException("absent cookie"))

            return responseServer.body()
                ?.toLoginInfo(password, cookie)
                ?.let { Result.success(it) }
                ?: Result.failure(IllegalStateException("Login response body is absent"))
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

    override suspend fun logout(cookie: String): Result<Unit> =
        try {
            val responseServer = api.logOut(cookie)
            if (responseServer.isSuccessful) {
                val logOutResponse: String? = responseServer.headers()["set-cookie"]
                if (logOutResponse?.checkEmptyCookie() == true) Result.success(Unit) else Result.failure(IllegalStateException("Cookie hasn't been deleted"))
            } else Result.failure(IllegalStateException("Logout exception"))

        } catch (ex: Exception) {
            Result.failure(ex)
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

    private fun String.checkEmptyCookie(): Boolean {
        val splitCookie: List<String> = split(";")
        return splitCookie.any {
            val splitPair = it.split("=")
            splitPair.size==2||splitPair[0].isBlank()||splitPair[1].isBlank()
        }
    }
}



