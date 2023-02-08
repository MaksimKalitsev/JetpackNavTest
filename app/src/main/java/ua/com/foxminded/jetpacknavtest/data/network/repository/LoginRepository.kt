package ua.com.foxminded.jetpacknavtest.data.network.repository

import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo
import ua.com.foxminded.jetpacknavtest.data.network.Api
import kotlin.Exception

interface ILoginRepository {
    suspend fun login(username: String, password: String): Result<LoginInfo>
    suspend fun logout(): Result<Unit>
}

class LoginRepository(
    private val api: Api
) : ILoginRepository {

    override suspend fun login(username: String, password: String): Result<LoginInfo> = try {
//        val requestTest = DataClass with(username, password)
//        api.signIn(requestTest)

//        val token = responseTest.headers().get("set-cookie")
//        and here we have to get the token

        TODO("Not yet implemented")
        //       Result.success()
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    override suspend fun logout(): Result<Unit> {
//        api.logOut(cookie = "Cookie")
//        I don't understand what we should get here
        TODO("Not yet implemented")
    }

}
