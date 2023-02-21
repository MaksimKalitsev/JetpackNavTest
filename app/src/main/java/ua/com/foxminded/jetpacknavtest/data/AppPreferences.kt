package ua.com.foxminded.jetpacknavtest.data

import android.content.Context
import android.content.SharedPreferences



interface IAppPreferences {
    var lastUserUsername: String?
}

class AppPreferences(
    private val sharedPreferences: SharedPreferences
) : IAppPreferences {

    companion object {
        private const val LAST_USERNAME_KEY = "LAST_USERNAME_KEY"
    }

    override var lastUserUsername: String?
        get() = sharedPreferences.getString(LAST_USERNAME_KEY, null)
        set(value) {
           sharedPreferences.edit().putString(LAST_USERNAME_KEY, value).apply()
        }
}