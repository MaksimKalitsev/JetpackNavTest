package ua.com.foxminded.jetpacknavtest.user

import android.content.Context
import kotlinx.coroutines.withContext
import ua.com.foxminded.jetpacknavtest.data.AppPreferences
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo
import ua.com.foxminded.jetpacknavtest.data.network.repository.ILoginRepository
import ua.com.foxminded.jetpacknavtest.di.UserComponent
import ua.com.foxminded.jetpacknavtest.di.appComponent
import ua.com.foxminded.jetpacknavtest.util.IDispatchersProvider

interface ILoginManager {
    val isUserLoggedIn: Boolean
    val userComponent: UserComponent?
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    fun createUserComponent(username: String)
}

class LoginManager (
    private val appContext: Context,
    private val appPreferences: AppPreferences,
    private val dispatchersProvider: IDispatchersProvider,
    private val loginRepository: ILoginRepository
): ILoginManager {

    override var userComponent: UserComponent? = null

    override val isUserLoggedIn: Boolean
        get() = userComponent?.userPreferences?.username?.isNotBlank() ?: false

    override suspend fun login(username: String, password: String): Result<Unit> {
        val result = withContext(dispatchersProvider.io) {
            loginRepository.login(username, password)
        }
        return if (result.isSuccess) {
            // todo: check presence of 6-th permission here
            // todo: get drivers list, and get current driver by email from this list
            finishNewUserLogin(result.getOrThrow())
            Result.success(Unit)
        } else Result.failure(result.exceptionOrNull() ?: Exception("Unknown exception"))
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun createUserComponent(username: String) {
        userComponent = appContext.appComponent.userComponent().create(username)
    }

    private fun finishNewUserLogin(loginInfo: LoginInfo) {
        createUserComponent(loginInfo.username)
        saveAsCurrentUser(loginInfo)
    }

    private fun saveAsCurrentUser(loginInfo: LoginInfo) {
        // todo: set cookie to authorization interceptor
        appPreferences.lastUserUsername = loginInfo.username
        with(userComponent!!.userPreferences) {
            clearUserData()
            username = loginInfo.username
            this.loginInfo = loginInfo
        }
    }

    private fun clearUserData() {
        userComponent!!.userPreferences.clearPreferences()
        appPreferences.lastUserUsername = null
        userComponent = null
    }
}