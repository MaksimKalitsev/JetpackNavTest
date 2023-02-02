package ua.com.foxminded.jetpacknavtest.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ua.com.foxminded.jetpacknavtest.data.AppPreferences
import ua.com.foxminded.jetpacknavtest.data.Const.APP_SHARED_PREFERENCES_NAME
import ua.com.foxminded.jetpacknavtest.data.IAppPreferences
import ua.com.foxminded.jetpacknavtest.data.network.Api
import ua.com.foxminded.jetpacknavtest.data.network.repository.ILoginRepository
import ua.com.foxminded.jetpacknavtest.data.network.repository.LoginRepository
import ua.com.foxminded.jetpacknavtest.di.qualifiers.AppSharedPrefs
import ua.com.foxminded.jetpacknavtest.user.ILoginManager
import ua.com.foxminded.jetpacknavtest.user.LoginManager
import ua.com.foxminded.jetpacknavtest.util.DispatchersProvider
import ua.com.foxminded.jetpacknavtest.util.IDispatchersProvider
import java.util.concurrent.ThreadLocalRandom

@Module(includes = [AppModule.Bindings::class])
class AppModule {

    @Module
    interface Bindings {
        @Binds
        fun bindAppPreferences(impl: AppPreferences): IAppPreferences

        @Binds
        fun bindLoginManager(impl: LoginManager): ILoginManager

        @Binds
        fun bindLoginRepository(impl: LoginRepository): ILoginRepository

        @Binds
        fun bindDispatchersProvider(impl: DispatchersProvider): IDispatchersProvider
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = TODO()

    @Provides
    @AppScope
    fun provideOkHttpClient(): OkHttpClient = TODO()

    @Provides
    @AppScope
    fun provideGson(): Gson = TODO()

    @Provides
    @AppScope
    fun provideDebuggingClass(): DebuggingClass = DebuggingClass("data: ${ThreadLocalRandom.current().nextInt(0, 100)}")

    @Provides
    @AppScope
    @AppSharedPrefs
    fun provideAppSharedPreferences(appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Provides
    @AppScope
    fun provideAppPreferencesImplementation(@AppSharedPrefs sharedPreferences: SharedPreferences) =
        AppPreferences(sharedPreferences)

    @Provides
    @AppScope
    fun provideLoginManager(
        context: Context,
        appPreferences: AppPreferences,
        dispatchersProvider: IDispatchersProvider,
        loginRepository: ILoginRepository
    ) = LoginManager(context, appPreferences, dispatchersProvider, loginRepository)

    @Provides
    @AppScope
    fun provideLoginRepository(api: Api) = LoginRepository(api)

    @Provides
    @AppScope
    fun provideDispatchersProvider() = DispatchersProvider()

    @Provides
    @AppScope
    fun provideApi(retrofit: Retrofit): Api = TODO("Not implemented")

}

data class DebuggingClass(val data: String)