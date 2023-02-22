package ua.com.foxminded.jetpacknavtest.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ua.com.foxminded.jetpacknavtest.data.AppPreferences
import ua.com.foxminded.jetpacknavtest.data.Const.APP_SHARED_PREFERENCES_NAME
import ua.com.foxminded.jetpacknavtest.data.IAppPreferences
import ua.com.foxminded.jetpacknavtest.data.network.Api
import ua.com.foxminded.jetpacknavtest.data.network.interceptors.UnzippingInterceptor
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

        @Binds
        fun bindInterceptor(impl: HttpLoggingInterceptor): Interceptor
    }

    @Provides
    @AppScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://fleet.kuantic.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(UnzippingInterceptor())
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
       return Gson()
    }

    @Provides
    @AppScope
    fun provideDebuggingClass(): DebuggingClass =
        DebuggingClass("data: ${ThreadLocalRandom.current().nextInt(0, 100)}")

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
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}

data class DebuggingClass(val data: String)