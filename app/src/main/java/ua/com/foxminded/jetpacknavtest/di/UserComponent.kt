package ua.com.foxminded.jetpacknavtest.di

import dagger.BindsInstance
import dagger.Subcomponent
import ua.com.foxminded.jetpacknavtest.data.IUserPreferences
import ua.com.foxminded.jetpacknavtest.di.qualifiers.Username

@Subcomponent(modules = [UserModule::class])
@UserScope
interface UserComponent {

    val userPreferences: IUserPreferences

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Username username: String
        ): UserComponent
    }
}