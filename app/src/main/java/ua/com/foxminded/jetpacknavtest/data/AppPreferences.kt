package ua.com.foxminded.jetpacknavtest.data

import android.content.SharedPreferences

interface IAppPreferences {
    var lastUserUsername: String?
}

class AppPreferences(
    sharedPreferences: SharedPreferences
): IAppPreferences  {

    override var lastUserUsername: String?
        get() = TODO("Not yet implemented")
        set(value) {
            TODO()
        }
}