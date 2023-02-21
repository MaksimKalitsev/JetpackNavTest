package ua.com.foxminded.jetpacknavtest.data

import android.content.SharedPreferences
import com.google.gson.Gson
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo

interface IUserPreferences {
    var username: String?
    var loginInfo: LoginInfo?
    var isPrivacyPolicyAccepted: Boolean
    fun clearPreferences()
}

class UserPreferences(
    private val sharedPreferences: SharedPreferences
) : IUserPreferences {

    companion object {

        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val PRIVACY_POLICY_KEY = "PRIVACY_POLICY_KEY"
        private const val LOGIN_INFO_KEY = "LOGIN_INFO_KEY"
    }

    private val gson = Gson()

    override var username: String?
        get() = sharedPreferences.getString(USERNAME_KEY, null)
        set(value) {
            sharedPreferences.edit().putString(USERNAME_KEY, value).apply()
        }
    override var isPrivacyPolicyAccepted: Boolean
        get() = sharedPreferences.getBoolean(PRIVACY_POLICY_KEY, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PRIVACY_POLICY_KEY, value).apply()
        }
    override var loginInfo: LoginInfo?
        get() = run {
            val json = sharedPreferences.getString(LOGIN_INFO_KEY, null)
            return gson.fromJson(json, LoginInfo::class.java)
        }
        set(value) {
            sharedPreferences.edit().putString(LOGIN_INFO_KEY, gson.toJson(value)).apply()

        }

    override fun clearPreferences(){
        sharedPreferences.edit().clear().apply()
    }
}