package ua.com.foxminded.jetpacknavtest.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import ua.com.foxminded.jetpacknavtest.data.IUserPreferences
import ua.com.foxminded.jetpacknavtest.data.UserPreferences
import ua.com.foxminded.jetpacknavtest.data.network.Api
import ua.com.foxminded.jetpacknavtest.data.network.repository.ITripsRepository
import ua.com.foxminded.jetpacknavtest.data.network.repository.TripsRepository
import ua.com.foxminded.jetpacknavtest.di.qualifiers.UserSharedPrefs
import ua.com.foxminded.jetpacknavtest.di.qualifiers.Username

@Module(includes = [UserModule.Bindings::class])
class UserModule {

    @Module
    interface Bindings {
        @Binds
        fun bindUserPreferences(impl: UserPreferences): IUserPreferences
        @Binds
        fun bindTripsRepository(impl: TripsRepository): ITripsRepository
    }

    @Provides
    @UserScope
    @UserSharedPrefs
    fun provideUserSharedPreferences(appContext: Context, @Username username: String): SharedPreferences =
        appContext.getSharedPreferences(username, Context.MODE_PRIVATE)

    @Provides
    @UserScope
    fun provideUserPreferences(@UserSharedPrefs sharedPreferences: SharedPreferences, gson: Gson): UserPreferences =
        UserPreferences(sharedPreferences, gson)

    @Provides
    @UserScope
    fun provideTripsRepository( prefs:UserPreferences, api: Api):TripsRepository =
        TripsRepository(prefs, api)
}