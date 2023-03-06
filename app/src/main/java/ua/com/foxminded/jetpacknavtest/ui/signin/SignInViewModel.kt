package ua.com.foxminded.jetpacknavtest.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.com.foxminded.jetpacknavtest.user.LoginManager
import javax.inject.Inject

class SignInViewModel : ViewModel() {

    @Inject
    lateinit var loginManager: LoginManager

    val progressLiveData = MutableLiveData<RequestState>()

    var isInitialized = false
        private set

    fun init() {
        if (isInitialized.not()) {
            isInitialized = true
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            progressLiveData.value = RequestState.LOADING
            delay(1000)
            loginManager.login(username, password).onSuccess {
                progressLiveData.value = RequestState.SUCCESS
            }.onFailure {
                progressLiveData.value = RequestState.ERROR
            }
        }
    }

}

