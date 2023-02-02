package ua.com.foxminded.jetpacknavtest.data

import android.content.SharedPreferences
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo

interface IUserPreferences {
    var username: String?
    var loginInfo: LoginInfo?
    var isPrivacyPolicyAccepted: Boolean
    fun clearPreferences()
}

class UserPreferences(
    private val  sharedPreferences: SharedPreferences
): IUserPreferences {
    override var username: String?
        get() = TODO("Not yet implemented")
        set(value) {
            TODO()
        }
    override var isPrivacyPolicyAccepted: Boolean
        get() = TODO("Not yet implemented")
        set(value) {
            TODO()
        }
    override var loginInfo: LoginInfo?
        get() = TODO("Not yet implemented")
        set(value) {
            TODO()
        }

    override fun clearPreferences() = TODO("Not yet implemented")
}