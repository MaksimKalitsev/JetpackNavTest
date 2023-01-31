package ua.com.foxminded.jetpacknavtest

import android.app.Application
import ua.com.foxminded.jetpacknavtest.di.AppComponent
import ua.com.foxminded.jetpacknavtest.di.DaggerAppComponent
import ua.com.foxminded.jetpacknavtest.di.DebuggingClass
import javax.inject.Inject

class App: Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var debuggingClass: DebuggingClass

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)

        appComponent.inject(this)
        println(debuggingClass.data)

    }
}