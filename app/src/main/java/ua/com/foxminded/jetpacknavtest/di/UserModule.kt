package ua.com.foxminded.jetpacknavtest.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import ua.com.foxminded.jetpacknavtest.data.IUserPreferences
import ua.com.foxminded.jetpacknavtest.data.UserPreferences
import ua.com.foxminded.jetpacknavtest.di.qualifiers.UserSharedPrefs
import ua.com.foxminded.jetpacknavtest.di.qualifiers.Username

@Module(includes = [UserModule.Bindings::class])
class UserModule {

    @Module
    interface Bindings {
        @Binds
        fun bindUserPreferences(impl: UserPreferences): IUserPreferences
    }

    @Provides
    @UserScope
    @UserSharedPrefs
    fun provideUserSharedPreferences(appContext: Context, @Username username: String): SharedPreferences =
        appContext.getSharedPreferences(username, Context.MODE_PRIVATE)

    @Provides
    @UserScope
    fun provideUserPreferences(@UserSharedPrefs sharedPreferences: SharedPreferences): UserPreferences =
        UserPreferences(sharedPreferences)
}