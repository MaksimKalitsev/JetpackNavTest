package ua.com.foxminded.jetpacknavtest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ua.com.foxminded.jetpacknavtest.App
import ua.com.foxminded.jetpacknavtest.ui.signin.FragmentSignIn

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context
        ): AppComponent
    }

    fun userComponent(): UserComponent.Factory

    fun inject(app: App)
    fun inject(fragment: FragmentSignIn)
}