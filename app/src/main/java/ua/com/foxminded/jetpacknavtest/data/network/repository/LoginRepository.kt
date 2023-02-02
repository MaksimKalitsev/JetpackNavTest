package ua.com.foxminded.jetpacknavtest.data.network.repository

import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo
import ua.com.foxminded.jetpacknavtest.data.network.Api

interface ILoginRepository {
    suspend fun login(username: String, password: String): Result<LoginInfo>
    suspend fun logout(): Result<Unit>
}

class LoginRepository(
    private val api: Api
): ILoginRepository {
    override suspend fun login(username: String, password: String): Result<LoginInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }

}
