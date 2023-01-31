package ua.com.foxminded.jetpacknavtest.di

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [UserModule::class])
@UserScope
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Named("username") username: String
        ): UserComponent
    }
}