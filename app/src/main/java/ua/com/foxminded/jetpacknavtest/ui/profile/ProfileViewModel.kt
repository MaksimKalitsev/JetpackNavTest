package ua.com.foxminded.jetpacknavtest.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.com.foxminded.jetpacknavtest.data.IUserPreferences
import ua.com.foxminded.jetpacknavtest.data.UserPreferences
import ua.com.foxminded.jetpacknavtest.ui.signin.RequestState
import ua.com.foxminded.jetpacknavtest.user.ILoginManager
import ua.com.foxminded.jetpacknavtest.user.LoginManager
import javax.inject.Inject

class ProfileViewModel : ViewModel() {

    @Inject
    lateinit var loginManager: ILoginManager
    @Inject
    lateinit var userPreferences: IUserPreferences

    val progressLiveData = MutableLiveData<RequestState>()

    fun logOut() {
        viewModelScope.launch {
            val cookie = userPreferences.loginInfo?.cookie
            if (cookie != null) {
                loginManager.logout(cookie).onSuccess {
                    progressLiveData.value = RequestState.SUCCESS
                }
            }
        }

    }


}