package ua.com.foxminded.jetpacknavtest.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import ua.com.foxminded.jetpacknavtest.data.network.responses.LoginResponse
import ua.com.foxminded.jetpacknavtest.user.LoginManager
import javax.inject.Inject

class SignInViewModel : ViewModel() {

    @Inject
    lateinit var loginManager: LoginManager

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
            // todo: show progress
            loginManager.login(username, password).onSuccess {
                // todo: hide progress
                //  todo: navigate to main screen. Don't forget to kill Authorization activity
            }.onFailure {
                // todo: show error snackbar with button "Retry"
            }

        }
    }

}

interface Api {
    @FormUrlEncoded
    @POST("login")
    suspend fun signIn(
        @Field("login") login: String,
        @Field("password") password: String,
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded"
    ): Response<LoginResponse>

    @POST("logout")
    suspend fun logOut(
        @Header("Cookie") cookie: String): Response<Unit>

}

fun main() = runBlocking {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fleet.kuantic.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
    val api: Api = retrofit.create(Api::class.java)

    val signInResponse: Response<LoginResponse> = api.signIn("olegkondratenko-driver", "P@ss4Cloudmade156!")
    val token = signInResponse.headers().get("set-cookie")
    val logoutResponse = token?.let { api.logOut(it) }
    val result = logoutResponse?.headers()?.get("set-cookie")


    println("LOGIN: $signInResponse")
    println("LOGOUT: $result")
}