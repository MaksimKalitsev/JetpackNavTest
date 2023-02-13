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
            loginManager.isUserLoggedIn
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

//interface Api {
//    @FormUrlEncoded
//    @POST("login")
//    suspend fun signIn(
//        @Field("login") login: String,
//        @Field("password") password: String,
//        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded"
//    ): Response<LoginResponse>
//
//    @POST("logout")
//    suspend fun logOut(
//        @Header("Cookie") cookie: String): Response<Unit>
//
//}
//
//fun main() = runBlocking {
//    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://fleet.kuantic.com/")
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create(Gson()))
//        .build()
//    val api: Api = retrofit.create(Api::class.java)
//
//    val signInResponse: Response<LoginResponse> = api.signIn("olegkondratenko-driver", "P@ss4Cloudmade156!")
//    val token = signInResponse.headers().get("set-cookie")
//    val logoutResponse = token?.let { api.logOut(it) }
//    val result = logoutResponse?.headers()?.get("set-cookie")
//
//
//    println("LOGIN: $signInResponse")
//    println("LOGOUT: $result")
//}