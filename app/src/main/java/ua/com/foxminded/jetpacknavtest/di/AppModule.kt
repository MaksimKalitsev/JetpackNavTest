package ua.com.foxminded.jetpacknavtest.di

import com.google.gson.Gson
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.ThreadLocalRandom

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = TODO()

    @Provides
    @AppScope
    fun provideOkHttpClient(

    ): OkHttpClient = TODO()

    @Provides
    @AppScope
    fun provideGson(): Gson = TODO()

    @Provides
    @AppScope
    fun provideDebuggingClass(): DebuggingClass = DebuggingClass("data: ${ThreadLocalRandom.current().nextInt(0, 100)}")

}

data class DebuggingClass(val data: String)