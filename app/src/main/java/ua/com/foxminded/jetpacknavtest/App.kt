package ua.com.foxminded.jetpacknavtest

import android.app.Application
import timber.log.Timber
import ua.com.foxminded.jetpacknavtest.di.AppComponent
import ua.com.foxminded.jetpacknavtest.di.DaggerAppComponent
import ua.com.foxminded.jetpacknavtest.di.DebuggingClass
import ua.com.foxminded.jetpacknavtest.user.ILoginManager
import javax.inject.Inject

class App: Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var debuggingClass: DebuggingClass

    @Inject
    lateinit var loginManager: ILoginManager

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this).apply {
            inject(this@App)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        println(debuggingClass.data)
        createUserComponentIfLoggedIn()
    }

    private fun createUserComponentIfLoggedIn() =
        appComponent.appPreferences
            .lastUserUsername
            ?.takeIf { loginManager.isUserLoggedIn }
            ?.let { username ->
                loginManager.createUserComponent(username)
            }
}